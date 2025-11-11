package org.nmj.mapper;

import org.nmj.dto.OrderHistoryDto;
import org.nmj.dto.ProductDto;
import org.nmj.dto.ShoppingCartDto;
import org.nmj.model.OrderHistory;

public class OrderHistoryMapper {
    public static OrderHistoryDto toDto(OrderHistory orderHistory){
        return new OrderHistoryDto(
                orderHistory.getId(),
                new ProductDto(
                        orderHistory.getProduct().getName(),
                        orderHistory.getProduct().getPrice(),
                        orderHistory.getProduct().getImageUrl()
                )
        );
    }
}
