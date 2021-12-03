
package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Product;

import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface ProductService {
    ProductModel createProduct(ProductModel productModel) throws Exception;

    GenericDataPagedResponse listProduct(CustomPageable pageable);

    ApiResponse updateProduct(ProductModel productModel) throws UserNameAlreadyExistException;

    void deleteProduct(Long id) throws Exception;

    Product getProduct(Long id) throws Exception;

}
