package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.SellerProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.SellerProfileNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface SellerProfileService {
    SellerProfileModel createSellerProfile(SellerProfileModel sellerProfileModel) throws Exception;

    GenericDataPagedResponse listSellerProfiles(CustomPageable pageable);

    ApiResponse updateSellerProfile(SellerProfileModel SellerprofileModel) throws Exception;

    void deleteSellerProfile(Long SellerprofileId) throws Exception;

    SellerProfile getSellerProfile(Long SellerprofileId) throws Exception;

}