package org.nmj.dto;

import org.nmj.model.Category;

import javax.persistence.Column;
import java.math.BigDecimal;

public class ProductDto {


    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private CategoryDto categoryDto;
    private Long createdBy;
    private String createdByUsername;
    private Long updatedBy;
    private String updatedByUsername;

    public ProductDto(String name, BigDecimal price, String imageUrl) {
        this.name=name;
        this.price=price;
        this.imageUrl=imageUrl;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }


    public ProductDto(Long id, String name, BigDecimal price, String description, String imageUrl, CategoryDto categoryDto, Long createdBy, Long updatedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryDto = categoryDto;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
    public ProductDto(){}

    public ProductDto initialize(String name, String description, String imageUrl, BigDecimal price, CategoryDto categoryDto, Long createdBy) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.categoryDto = categoryDto;
        this.createdBy = createdBy;
        return this;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updateBy) {
        this.updatedBy = updateBy;
    }

    public String getUpdatedByUsername() {
        return updatedByUsername;
    }

    public void setUpdatedByUsername(String updatedByUsername) {
        this.updatedByUsername = updatedByUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
