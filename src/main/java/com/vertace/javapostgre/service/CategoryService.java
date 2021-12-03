
package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Category;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface CategoryService {
    CategoryModel createCategory(CategoryModel categoryModel) throws Exception;

    GenericDataPagedResponse listCategory(CustomPageable pageable);

    ApiResponse updateCategory(CategoryModel categoryModel) throws UserNameAlreadyExistException;

    void deleteCategory(Long id) throws Exception;

    Category getCategory(Long id) throws Exception;

}


