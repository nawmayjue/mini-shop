package org.nmj.service;

import org.hibernate.Session;
import org.nmj.config.HibernateUtil;
import org.nmj.dao.CategoryDao;
import org.nmj.dao.CategoryDaoImpl;
import org.nmj.dao.UserDao;
import org.nmj.dao.UserDaoImpl;
import org.nmj.dto.CategoryDto;
import org.nmj.mapper.CategoryMapper;
import org.nmj.model.Category;
import org.nmj.model.User;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImplement implements CategoryService {
    //    @Override
//    public void create(String name) throws Exception {
//        CategoryDao categoryDao;
//        public CategoryServiceImplement(){
//            this.categoryDao = new CategoryDaoImpl();
//        }
//
//        @Override
//        public void create(String name) throws Exception{
//            categoryDao.save(
//                    new Category().initialize(new)
//            );
//        }
//    }
//}
    private CategoryDao categoryDao;
    private UserDao userDao;

    public CategoryServiceImplement() {

        this.categoryDao = new CategoryDaoImpl();
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void create(String name, Long createdBy) throws Exception {
        categoryDao.save(
                new Category().initialize(name, createdBy)
        );
    }

    @Override
    public List<CategoryDto> retrieveAll() throws Exception {
        List<Category> categories = categoryDao.findAll();
        if(categories.isEmpty()){
            return null;
        }
        if (categories == null) {
            return null;
        }
        System.out.println(categories);
        List<CategoryDto> categoryDtoList = categories.stream().map(CategoryMapper::toDto).toList();
        for (CategoryDto categoryDto: categoryDtoList){
            if(categoryDto.getCreatedBy() != 0L){
                categoryDto.setCreatedByUsername(
                        userDao.findById(
                                        categoryDto.getCreatedBy()
                                )
                                .getUsername()
                );
            }

            if(categoryDto.getUpdatedBy() != 0L){
                categoryDto.setUpdatedByUsername(
                        userDao.findById(
                                        categoryDto.getUpdatedBy()
                                )
                                .getUsername()
                );
            }
        }

        /*
        List<CategoryDto> categoryDtoList = new ArrayList<>()'
        for(Category category : categories){
            categoryDtoList.add(CategoryMapper.toDto(category));
        }

*/
        return categoryDtoList;
    }

    @Override
    public void edit(Long id, String name, Long updatedBy) throws Exception {
        Category category = categoryDao.findById(id);
        category.setName(name);
        category.setUpdatedBy(updatedBy);
        categoryDao.update(category);

    }

    @Override
    public void delete(Long id) throws Exception {
        categoryDao.delete(id);
    }

    @Override
    public CategoryDto retrieveOne(Long id) throws Exception {
        Category category = categoryDao.findById(id);
        return CategoryMapper.toDto(category);
    }

}
