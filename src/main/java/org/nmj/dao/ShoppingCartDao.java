package org.nmj.dao;

import org.nmj.model.Category;
import org.nmj.model.ShoppingCart;
import org.nmj.model.ShoppingCartData;

import java.util.List;


public interface ShoppingCartDao {
    void save(ShoppingCartData shoppingCart)throws Exception;
    List<ShoppingCartData> findAllByUserId(Long userId)throws Exception;
    void delete(Long userId, Long productId)throws Exception;
    boolean existsByUserIdAndProductId(Long userId, Long productId)throws Exception;
    ShoppingCartData findOneByUserIdAndProductId(Long userId, Long productId)throws Exception;
    void updateQuantity(ShoppingCartData shoppingCart)throws Exception;
}