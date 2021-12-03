package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Category;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.CategoryRepository;
import com.vertace.javapostgre.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) throws Exception {
        checkIfUserNameUnique(categoryModel.getName(), -99L);
        Category category = modelMapperUtil.convertToObject(categoryModel, Category.class);
        categoryRepository.save(category);
        //emailService.triggerCreateCategoryMail(Category);
        return modelMapperUtil.convertToObject(category, CategoryModel.class);
    }

    private void checkIfUserNameUnique(String userName, Long userId) throws UserNameAlreadyExistException {
        Category userByCategoryName = categoryRepository.findByName(userName);
        if (userByCategoryName != null && !userByCategoryName.getId().equals(userId)) {
            throw new UserNameAlreadyExistException("Category Name already exist");
        }
    }


    @Override
    public GenericDataPagedResponse listCategory(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Category> allCategory = categoryRepository.findAll(pageAble);
        System.out.println(allCategory);
        GenericDataPagedResponse getCategoryResponse = new GenericDataPagedResponse();
        List<CategoryModel> userModels = modelMapperUtil.convertToList(allCategory.getContent(), CategoryModel.class);
        if (!userModels.isEmpty())
            getCategoryResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allCategory.getTotalPages(), allCategory.getTotalElements());
        getCategoryResponse.setPageResponse(pageResponse);
        return getCategoryResponse;
    }

    @Override
    public ApiResponse updateCategory(CategoryModel categoryModel) throws UserNameAlreadyExistException {
        Optional<Category> Category = categoryRepository.findById(categoryModel.getId());
        if (Category.isPresent()) {
            Category userObj = Category.get();
            if (categoryModel.getName() != null) {
                checkIfUserNameUnique(categoryModel.getName(), Category.get().getId());
            }
            if (categoryModel.getIsDeleted() != null) {
                userObj.setIsDeleted(categoryModel.getIsDeleted());
            }
            categoryRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteCategory(Long userId) throws Exception {
        categoryRepository.deleteById(userId);
    }

    @Override
    public Category getCategory(Long id) throws Exception {
        System.out.println(id);
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("Invalid CategoryId"));
    }
}
