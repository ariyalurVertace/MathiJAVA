package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.Order;
import com.vertace.javapostgre.entity.Product;
import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private Long id;

    private String name; 

    private Order order;

    private Product product; 

    private Boolean isDeleted = false;
}