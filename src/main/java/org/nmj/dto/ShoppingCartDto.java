package org.nmj.dto;

public class ShoppingCartDto {
    private Long id;
    private ProductDto productDto;

    public ShoppingCartDto(){}

    public ShoppingCartDto(Long id, ProductDto productDto) {
        this.id=id;
        this.productDto=productDto;
    }

    public ShoppingCartDto initialize(ProductDto productDto){
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
