package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.District;
import com.vertace.javapostgre.entity.State;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    private Long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private Integer pincode;
    private String landmark;
    private District district; 
    private State state; 
    private Boolean isDeleted = false;

    
}