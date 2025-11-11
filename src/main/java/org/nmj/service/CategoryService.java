package org.nmj.service;

import org.nmj.dto.CategoryDto;
import org.nmj.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void create(String name, Long createdBy) throws Exception;
    List<CategoryDto> retrieveAll() throws Exception;
    void edit(Long id, String name, Long updatedBy) throws Exception;
    void delete(Long id) throws Exception;
    CategoryDto retrieveOne(Long id)throws Exception;
}
