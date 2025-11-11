package org.nmj.service;


import org.nmj.dto.ProductDto;
import org.nmj.model.Product;

import java.math.BigDecimal;
import java.util.List;


public interface ProductService {
    void create(ProductDto productDto) throws Exception;
    List<ProductDto> retrieveAll()throws Exception;
    void edit(ProductDto productDto) throws Exception;
    void delete(Long id)throws Exception;
    ProductDto retrieveOne(Long id) throws Exception;

}
