package org.nmj.service;

import org.nmj.config.DatabaseConfig;
import org.nmj.dao.ProductDao;
import org.nmj.dao.ProductDaoImpl;
import org.nmj.dao.ShoppingCartDao;
import org.nmj.dao.ShoppingCartDaoImpl;
import org.nmj.dto.ShoppingCartDto;
import org.nmj.mapper.ProductMapper;
import org.nmj.mapper.ShoppingCartMapper;
import org.nmj.model.Category;
import org.nmj.model.Product;
import org.nmj.model.ShoppingCart;
import org.nmj.model.ShoppingCartData;

import java.math.BigDecimal;
import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = new ShoppingCartDaoImpl(DatabaseConfig.getDataSource());
    }

    @Override
    public void add(Long userId, Long productId, Integer quantity) throws Exception {
        if(shoppingCartDao.existsByUserIdAndProductId(userId, productId)) {
            ShoppingCartData shoppingCartData = shoppingCartDao.findOneByUserIdAndProductId(userId, productId);
            shoppingCartDao.updateQuantity(
                    new ShoppingCartData(
                            shoppingCartData.getId(),
                            userId,
                            productId,
                            shoppingCartData.getQuantity() + quantity,
                            null,
                            BigDecimal.ZERO,
                            BigDecimal.ZERO
                    )
            );
        } else {
            shoppingCartDao.save(
                    new ShoppingCartData(0L, userId, productId, quantity, null, BigDecimal.ZERO, BigDecimal.ZERO)
            );
        }
    }

    @Override
    public List<ShoppingCartData> retrieveAll(Long userId) throws Exception {
        return shoppingCartDao.findAllByUserId(userId);
    }

    @Override
    public void remove(Long userId, Long productId) throws Exception {
        shoppingCartDao.delete(userId, productId);
    }

    @Override
    public void updateQuantity(Long id, Integer quantity, Long userId, Long productId) throws Exception {
        if(quantity !=0){
            shoppingCartDao.updateQuantity(
                    new ShoppingCartData(
                            id,
                            userId,
                            productId,
                            quantity,
                            null,
                            BigDecimal.ZERO,
                            BigDecimal.ZERO
                    )
            );
        } else{
            shoppingCartDao.delete(userId, productId);
        }
    }
}