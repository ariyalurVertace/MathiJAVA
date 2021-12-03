
package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.entity.Category;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService; 

    @PostMapping
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel) throws Exception {
        try {
            return categoryService.createCategory(categoryModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getcategory(@RequestBody CustomPageable customPageable) {
        return categoryService.listCategory(customPageable);
    }

    @PutMapping
    public ApiResponse updateCategory(@RequestBody CategoryModel categoryModel) throws UserNameAlreadyExistException {
        return categoryService.updateCategory(categoryModel);
    }

    
    
}
