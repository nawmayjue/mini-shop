package org.nmj.dto;

public class OrderHistoryDto {
    private Long id;
    private ProductDto productDto;

    public OrderHistoryDto(){}

    public OrderHistoryDto(Long id, ProductDto productDto) {
        this.id = id;
        this.productDto = productDto;
    }

    public OrderHistoryDto initialize(ProductDto productDto){
        this.productDto=productDto;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
