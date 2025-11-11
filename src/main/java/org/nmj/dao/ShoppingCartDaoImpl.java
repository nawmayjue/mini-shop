package org.nmj.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nmj.config.HibernateUtil;
import org.nmj.model.Category;
import org.nmj.model.ShoppingCart;
import org.nmj.model.ShoppingCartData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<ShoppingCartData> ROW_MAPPER = new ShoppingCartRowMapper();
    private static final RowMapper<ShoppingCartData> FIND_ONE_ROW_MAPPER = new ShoppingCartFindOneRowMapper();

    public ShoppingCartDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(ShoppingCartData shoppingCart) throws Exception {
        String sql = "INSERT INTO shopping_cart(user_id, product_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                shoppingCart.getUserId(), shoppingCart.getProductId(), shoppingCart.getQuantity()
        );
    }

    @Override
    public List<ShoppingCartData> findAllByUserId(Long userId) throws Exception {
        String sql = """
                SELECT 
                    MAX(s.id) AS id, 
                    u.id AS userId, 
                    MAX(s.quantity) AS quantity,
                    p.id AS product_id, 
                    p.name AS product_name, 
                    p.price AS product_price, 
                    (SELECT SUM(p2.price * s2.quantity)
                         FROM shopping_cart s2
                         JOIN products p2 ON p2.id = s2.product_id
                         WHERE s2.user_id = s.user_id) AS total_price 
                FROM shopping_cart s 
                JOIN products p ON p.id=s.product_id 
                JOIN users u ON u.id=s.user_id
                WHERE user_id=?
                GROUP BY u.id, p.id
                """;
        return jdbcTemplate.query(
                sql,
                ROW_MAPPER,
                userId
        );
    }

    @Override
    public void delete(Long userId, Long productId) throws Exception {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(
                sql,
                userId, productId
        );
    }


    @Override
    public boolean existsByUserIdAndProductId(Long userId, Long productId) throws Exception {
        String sql = "SELECT COUNT(*) FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Long.class,
                userId, productId
        ) > 0; // If 0 -> false, if > 0 -> true
    }

    @Override
    public ShoppingCartData findOneByUserIdAndProductId(Long userId, Long productId) throws Exception {
        String sql = """
            SELECT 
                id, user_id AS userId, product_id, quantity
            FROM 
                shopping_cart
            WHERE 
                user_id = ? AND product_id = ?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                FIND_ONE_ROW_MAPPER,
                userId, productId
        );
    }

    @Override
    public void updateQuantity(ShoppingCartData shoppingCart) throws Exception {
        System.out.println("quantity: "+ shoppingCart.getQuantity() + " id: " + shoppingCart.getId());
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                shoppingCart.getQuantity(), shoppingCart.getId()
        );
    }

    private static class ShoppingCartRowMapper implements RowMapper<ShoppingCartData> {

        @Override
        public ShoppingCartData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ShoppingCartData(
                    rs.getLong("id"),
                    rs.getLong("userId"),
                    rs.getLong("product_id"),
                    rs.getInt("quantity"),
                    rs.getString("product_name"),
                    rs.getBigDecimal("product_price"),
                    rs.getBigDecimal("total_price")
            );
        }
    }

    private static class ShoppingCartFindOneRowMapper implements RowMapper<ShoppingCartData> {

        @Override
        public ShoppingCartData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ShoppingCartData(
                    rs.getLong("id"),
                    rs.getLong("userId"),
                    rs.getLong("product_id"),
                    rs.getInt("quantity"),
                    null,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            );
        }
    }
}
