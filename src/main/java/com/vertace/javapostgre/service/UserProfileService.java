package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.UserProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface UserProfileService {
    UserProfileModel createUserProfile(UserProfileModel userProfileModel) throws Exception;

    GenericDataPagedResponse listUserProfiles(CustomPageable pageable);

    ApiResponse updateUserProfile(UserProfileModel userProfileModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException;

    void deleteUserProfile(Long userProfileId) throws Exception;

    UserProfile getUserProfile(Long id) throws Exception;

}
