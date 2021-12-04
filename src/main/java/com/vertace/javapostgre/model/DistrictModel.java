package com.vertace.javapostgre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vertace.javapostgre.entity.State;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictModel {
    private Long id;

    private String name; 

    private State state; 

    private Boolean isDeleted = false;

    
}