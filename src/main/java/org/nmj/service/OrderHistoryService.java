package org.nmj.service;

import org.nmj.model.OrderHistoryData;

import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryService {
    void add(Long userId, Long productId, Integer quantity) throws Exception;
    List<OrderHistoryData> retrieveAll(Long userId) throws Exception;
    List<OrderHistoryData> retrieveAllForAdmin() throws Exception;
}
