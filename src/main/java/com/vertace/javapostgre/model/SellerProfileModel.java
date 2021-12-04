package com.vertace.javapostgre.model;

import com.vertace.javapostgre.entity.Address;
import com.vertace.javapostgre.entity.SellerProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProfileModel {
   
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private SellerProfile sellerProfile;
    
    private Address address;
    
   // private UserId userId;
    
    private boolean isApproved = false;

    private Boolean isDeleted = false;
}