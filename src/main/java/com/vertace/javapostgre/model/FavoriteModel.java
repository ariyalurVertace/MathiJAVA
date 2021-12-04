package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FavoriteModel{
    private Long id;

    private String name; 

    private Product product;

    private Boolean isDeleted = false;

    public Product getProduct() {
        return this.product;
        }
        
    public void setProduct(Product product) {
        this.product = product;
        }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
}