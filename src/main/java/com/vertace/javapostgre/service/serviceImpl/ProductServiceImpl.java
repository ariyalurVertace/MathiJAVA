package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Product;
//import com.vertace.javapostgre.entity.District;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.ProductRepository;
//import com.vertace.javapostgre.repository.DistrictRepository;
import com.vertace.javapostgre.service.ProductService;
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
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    //private final DistrictRepository districtRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ProductModel createProduct(ProductModel productModel) throws Exception {
        //Long categoryId = productModel.getCategory().getId();
        //District district = districtRepository.findById(districtId).orElseThrow(() -> new Exception("Invalid DistrictId"));
        Product product=modelMapperUtil.convertToObject(productModel, Product.class);
        //address.setDistrict(district);
        productRepository.save(product);
        //emailService.triggerCreateAddressMail(address);
        return modelMapperUtil.convertToObject(product, ProductModel.class);
    }

    @Override
    public GenericDataPagedResponse listProduct(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Product> allProducts = productRepository.findAll(pageAble);
        System.out.println(allProducts);
        GenericDataPagedResponse getProductResponse = new GenericDataPagedResponse();
        System.out.println(allProducts.getContent());
        List<ProductModel> userModels = modelMapperUtil.convertToList(allProducts.getContent(), ProductModel.class);
        if (!userModels.isEmpty())
            getProductResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allProducts.getTotalPages(), allProducts.getTotalElements());
        getProductResponse.setPageResponse(pageResponse);
        return getProductResponse;
    }

    @Override
    public ApiResponse updateProduct(ProductModel productModel) {
        Optional<Product> product = productRepository.findById(productModel.getId());
        if (product.isPresent()) {
            Product userObj = product.get();
            if (productModel.getIsDeleted() != null) {
                userObj.setIsDeleted(productModel.getIsDeleted());
            }
            //if (productModel.getDistrict() != null) {
               // userObj.setDistrict(productModel.getDistrict());
            //}
            productRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteProduct(Long userId) throws Exception {
        productRepository.deleteById(userId);
    }

    @Override
    public Product getProduct(Long id) throws Exception {
        System.out.println(id);
        return productRepository.findById(id).orElseThrow(() -> new Exception("Invalid ProductId"));
    }

}
