package org.nmj.service;

import org.nmj.dto.ShoppingCartDto;
import org.nmj.model.ShoppingCartData;


import java.util.List;

public interface ShoppingCartService {
    void add(Long userId, Long productId, Integer quantity) throws Exception;
    List<ShoppingCartData> retrieveAll(Long userId) throws Exception;
    void remove(Long userId, Long productId) throws Exception;
    void updateQuantity(Long id, Integer quantity, Long userId, Long productId) throws Exception;
}
