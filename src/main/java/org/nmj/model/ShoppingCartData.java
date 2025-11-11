package org.nmj.model;

import java.math.BigDecimal;

public class ShoppingCartData{
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String productName;
    private BigDecimal productPrice;
    private  BigDecimal totalPrice;

    public ShoppingCartData(){}

    public ShoppingCartData(Long id, Long userId, Long productId, Integer quantity, String productName, BigDecimal productPrice, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
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

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

