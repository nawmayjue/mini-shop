package org.nmj.dao;

import org.nmj.model.OrderHistory;
import org.nmj.model.OrderHistoryData;
import org.nmj.model.ShoppingCartData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderHistoryDaoImpl implements OrderHistoryDao{

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<OrderHistoryData> ROW_MAPPER = new OrderHistoryDaoImpl.OrderHistoryRowMapper();

    public OrderHistoryDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(OrderHistoryData orders) throws Exception {
        String sql = "INSERT INTO order_histories(user_id, product_id, quantity, order_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                orders.getUserId(), orders.getProductId(), orders.getQuantity(), orders.getOrderedDate()
        );
    }

    @Override
    public List<OrderHistoryData> findAllByUserId(Long userId) throws Exception {
        String sql = """
                SELECT
                o.id AS id,
                u.id AS user_id,
                p.id AS product_id,
                o.quantity AS quantity,
                p.name AS product_name,
                p.price AS product_price,
                0 AS total_price,
                o.order_date AS ordered_date
                FROM order_histories o
                JOIN products p ON p.id=o.product_id
                JOIN users u ON u.id=o.user_id
                WHERE user_id=?
                GROUP BY o.id, u.id
                """;
        return jdbcTemplate.query(
                sql,
                ROW_MAPPER,
                userId
        );
    }

    @Override
    public List<OrderHistoryData> findAll() throws Exception {
        String sql = """
                SELECT
                o.id AS id,
                u.id AS user_id,
                p.id AS product_id,
                o.quantity AS quantity,
                p.name AS product_name,
                p.price AS product_price,
                0 AS total_price,
                o.order_date AS ordered_date
                FROM order_histories o
                JOIN products p ON p.id=o.product_id
                JOIN users u ON u.id=o.user_id
                GROUP BY o.id, u.id
                """;
        return jdbcTemplate.query(
                sql,
                ROW_MAPPER
        );
    }

    private static class OrderHistoryRowMapper implements RowMapper<OrderHistoryData> {

        @Override
        public OrderHistoryData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OrderHistoryData(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getLong("product_id"),
                    rs.getInt("quantity"),
                    rs.getString("product_name"),
                    rs.getBigDecimal("product_price"),
                    rs.getBigDecimal("total_price"),
                    rs.getTimestamp("ordered_date") != null
                            ? rs.getTimestamp("ordered_date").toLocalDateTime()
                            : null
            );
        }
    }
}
