package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.Cart;
import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.entity.UserProfile;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartModel {
    private Long id;

    private String count;

    private Product product;

    private UserProfile userProfile;

    private Boolean isDeleted = false;
}