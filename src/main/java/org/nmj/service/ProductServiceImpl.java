package org.nmj.service;

import org.nmj.dao.*;
import org.nmj.dto.CategoryDto;
import org.nmj.dto.ProductDto;
import org.nmj.mapper.CategoryMapper;
import org.nmj.mapper.ProductMapper;
import org.nmj.model.Category;
import org.nmj.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    ProductDao productDao;
    CategoryDao categoryDao;
    UserDao userDao;

    public ProductServiceImpl(){
        this.productDao = new ProductDaoImpl();
        this.categoryDao = new CategoryDaoImpl();
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void create(ProductDto productDto) throws Exception {
        List<Product> products = productDao.findAll();
        Category category = categoryDao.findById(productDto.getCategoryDto().getId());
        productDao.save(
                new Product().initialize(productDto, category, productDto.getCreatedBy())
        );
    }

    @Override
    public List<ProductDto> retrieveAll() throws Exception {
        List<Product> products = productDao.findAll();
        System.out.println(products.size());
        if(products.isEmpty()){
            System.out.println(products+"one");
            return null;
        }
        if (products == null) {
            System.out.println(products+"two");
            return null;
        }
        List<ProductDto> productDtoList = products.stream().map(ProductMapper::toDto).toList();
        System.out.println(productDtoList.size());
        for (ProductDto productDto: productDtoList) {
            if (productDto.getCreatedBy() != 0L && productDto.getCreatedBy() != null) {
                productDto.setCreatedByUsername(
                        userDao.findById(
                                        productDto.getCreatedBy()
                                )
                                .getUsername()
                );
            }

            if (productDto.getUpdatedBy() != null ) {

                productDto.setUpdatedByUsername(
                        userDao.findById(
                                        productDto.getUpdatedBy()
                                )
                                .getUsername()
                );
            }
        }
        return productDtoList;
    }

    @Override
    public void edit(ProductDto productDto) throws Exception {
        Product product = productDao.findById(productDto.getId());

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setUpdatedBy(productDto.getUpdatedBy());// include image URL update

        productDao.update(product);
    }


    @Override
    public void delete(Long id) throws Exception {
        productDao.delete(id);
    }

    @Override
    public ProductDto retrieveOne(Long id) throws Exception {
        Product product = productDao.findById(id);
        return ProductMapper.toDto(product);
    }
}
