package org.nmj.mapper;

import org.nmj.dto.CategoryDto;
import org.nmj.dto.ProductDto;
import org.nmj.model.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImageUrl(),
                new CategoryDto(
                        product.getCategory().getId(),
                        product.getCategory().getName(),
                        product.getCategory().getCreatedBy()
                ),
                product.getCreatedBy(),
                product.getUpdatedBy()
        );
    }
}
