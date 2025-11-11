package org.nmj.dao;

import org.nmj.model.Category;

import java.util.List;

public interface CategoryDao {
    void save(Category category) throws Exception;
    List<Category> findAll()throws Exception;
    void update(Category category) throws Exception;
    void delete(Long id) throws Exception;
    Category findById(Long id) throws Exception;
}
