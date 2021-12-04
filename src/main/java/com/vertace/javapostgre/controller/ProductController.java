package com.vertace.javapostgre.controller;


import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @PostMapping
    public ProductModel createCart(@RequestBody ProductModel productModel) throws Exception {
        try {
            return productService.createProduct(productModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getProduct(@RequestBody CustomPageable customPageable) {
        return productService.listProducts(customPageable);} 

        @PutMapping
    public ApiResponse updateProduct(@RequestBody ProductModel productModel) throws Exception {
        return productService.updateProduct(productModel);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam("id") Long id) throws Exception {
        productService.deleteProduct(id);
    }
    @GetMapping
    public ResponseEntity<?> getProduct(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(productService.getProduct(id));
    }
    }