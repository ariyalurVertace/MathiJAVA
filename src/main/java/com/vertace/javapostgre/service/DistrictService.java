package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.District;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface DistrictService {
    DistrictModel createDistrict(DistrictModel districtModel) throws Exception;

    GenericDataPagedResponse listDistricts(CustomPageable pageable);

    ApiResponse updateDistrict(DistrictModel districtModel) throws UserNameAlreadyExistException;

    void deleteDistrict(Long id) throws Exception;

    District getDistrict(Long id) throws Exception;

}
