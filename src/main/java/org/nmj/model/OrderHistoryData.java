package org.nmj.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderHistoryData extends OrderHistory {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String productName;
    private BigDecimal productPrice;
    private  BigDecimal totalPrice;
    private LocalDateTime orderedDate;



    public OrderHistoryData(){}

    public OrderHistoryData(Long id, Long userId, Long productId, Integer quantity, String productName, BigDecimal productPrice, BigDecimal totalPrice, LocalDateTime orderedDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.orderedDate= orderedDate;
    }

    public OrderHistoryData(Long id, Long userId, Long productId, Integer quantity, String productName, BigDecimal productPrice, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.orderedDate= LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public LocalDateTime getOrderedDate() {
        return orderedDate;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
