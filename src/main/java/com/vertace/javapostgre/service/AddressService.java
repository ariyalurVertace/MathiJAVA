package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Address;
import com.vertace.javapostgre.model.*;
public interface AddressService {
    AddressModel createAddress(AddressModel addressModel) throws Exception;

    GenericDataPagedResponse listAddresses(CustomPageable pageable);

    ApiResponse updateAddress(AddressModel addressModel) throws Exception;

    void deleteAddress(Long id) throws Exception;

    Address getAddress(Long id) throws Exception;
    
}