package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.entity.Product;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

// @Table (name="Banner", schema="Banner")
public class BannerModel {
    private Long id;
    private String Name;
    private String url;
    private boolean isActive = false;
    private Product product;
    private Boolean isDeleted = false;
}
