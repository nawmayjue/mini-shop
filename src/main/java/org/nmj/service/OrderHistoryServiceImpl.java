package org.nmj.service;

import org.nmj.config.DatabaseConfig;
import org.nmj.dao.OrderHistoryDao;
import org.nmj.dao.OrderHistoryDaoImpl;
import org.nmj.model.OrderHistory;
import org.nmj.model.OrderHistoryData;
import org.nmj.model.ShoppingCartData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryServiceImpl implements OrderHistoryService{
    private OrderHistoryDao orderHistoryDao;
    public OrderHistoryServiceImpl(OrderHistoryDao orderHistoryDao){
        this.orderHistoryDao = new OrderHistoryDaoImpl(DatabaseConfig.getDataSource());
    }


    @Override
    public void add(Long userId, Long productId, Integer quantity) throws Exception {
        orderHistoryDao.save(
                new OrderHistoryData(0L,userId, productId, quantity, null, BigDecimal.ZERO, BigDecimal.ZERO)
        );
    }

    @Override
    public List<OrderHistoryData> retrieveAll(Long userId) throws Exception {
//        return orderHistoryDao.findAllByUserId(userId);


        return orderHistoryDao.findAllByUserId(userId).stream().map(
                orderHistoryData -> {
                    if (orderHistoryData.getQuantity() != null && orderHistoryData.getProductPrice() !=null) {
                        orderHistoryData.setTotalPrice(BigDecimal.valueOf(orderHistoryData.getQuantity())
                                .multiply(orderHistoryData.getProductPrice()));
                    } else {
                        orderHistoryData.setTotalPrice(BigDecimal.ZERO);
                    }
                    return orderHistoryData;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<OrderHistoryData> retrieveAllForAdmin() throws Exception {
        return orderHistoryDao.findAll();
    }

}
