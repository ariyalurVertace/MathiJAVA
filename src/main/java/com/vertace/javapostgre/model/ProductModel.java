package com.vertace.javapostgre.model;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.Product;

//import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private Long id;

    private String name; 

    private Product product; 

    private Boolean isDeleted = false;
}