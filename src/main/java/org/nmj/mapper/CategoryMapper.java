package org.nmj.mapper;

import org.nmj.dto.CategoryDto;
import org.nmj.model.Category;

public class CategoryMapper {

    public static CategoryDto toDto(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getCreatedBy(),
                category.getUpdatedBy()
        );
    }
}
