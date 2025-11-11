package org.nmj.mapper;

import org.nmj.dto.ProductDto;
import org.nmj.dto.ShoppingCartDto;
import org.nmj.model.ShoppingCart;

public class ShoppingCartMapper {

    public static ShoppingCartDto toDto(ShoppingCart shoppingCart){
        return new ShoppingCartDto(
                shoppingCart.getId(),
                new ProductDto(
                        shoppingCart.getProduct().getName(),
                        shoppingCart.getProduct().getPrice(),
                        shoppingCart.getProduct().getImageUrl()
                )
        );
    }
}
