package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.UserProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserProfileNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface UserProfileService {
    UserProfileModel createUserProfile(UserProfileModel userModel) throws Exception;

    GenericDataPagedResponse listUserProfiles(CustomPageable pageable);

    ApiResponse updateUserProfile(UserProfileModel userprofileModel) throws EmailIdAlreadyExistException, UserProfileNameAlreadyExistException;

    void deleteUserProfile(Long userprofileId) throws Exception;

    UserProfile getUserProfile(Long userprofileId) throws Exception;

}
