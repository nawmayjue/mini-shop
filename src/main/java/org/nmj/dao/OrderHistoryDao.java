package org.nmj.dao;

import org.nmj.model.OrderHistory;
import org.nmj.model.OrderHistoryData;

import java.util.List;

public interface OrderHistoryDao {
    void save(OrderHistoryData orders) throws Exception;
    List<OrderHistoryData> findAllByUserId(Long userId) throws Exception;
    List<OrderHistoryData> findAll() throws Exception;
}
