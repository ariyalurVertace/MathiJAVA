package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.UserProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.UserProfileRepository;
import com.vertace.javapostgre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserProfileModel createUserProfile(UserProfileModel userProfileModel) throws Exception {
        checkIfEmailUnique(userProfileModel.getEmail(), -99L);
        checkIfUserNameUnique(userProfileModel.getEmail(), -99L);
        if (userProfileModel.getPassword() == null || userProfileModel.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }
        UserProfile userProfile = modelMapperUtil.convertToObject(userProfileModel, UserProfile.class);
        userProfile.setPassword(userProfileModel.getPassword());
        userProfileRepository.save(userProfile);
        //emailService.triggerCreateUserProfileMail(userProfile);
        return modelMapperUtil.convertToObject(userProfile, UserProfileModel.class);
    }

    private void checkIfUserNameUnique(String userName, Long userId) throws UserNameAlreadyExistException {
        UserProfile userProfileByUserProfileName = userProfileRepository.findByUserName(userName);
        if (userProfileByUserProfileName != null && !userProfileByUserProfileName.getId().equals(userId)) {
            throw new UserNameAlreadyExistException("UserProfile Name already exist");
        }
    }

    private void checkIfEmailUnique(String email, Long userId) throws EmailIdAlreadyExistException {
        UserProfile userProfileByEmail = userProfileRepository.findByEmail(email);
        if (userProfileByEmail == null) {
            return;
        }
        if (!userProfileByEmail.getId().equals(userId)) {
            throw new EmailIdAlreadyExistException("Email id already exist");
        }
    }

    @Override
    public GenericDataPagedResponse listUserProfiles(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<UserProfile> allUserProfiles = userProfileRepository.findAll(pageAble);
        System.out.println(allUserProfiles);
        List<UserProfileModel> userProfileModels = modelMapperUtil.convertToList(allUserProfiles.getContent(), UserProfileModel.class);
        GenericDataPagedResponse getUserProfileResponse= new GenericDataPagedResponse();;
        if (!userProfileModels.isEmpty())
        getUserProfileResponse.setData(userProfileModels);
        PageResponse pageResponse = new PageResponse(allUserProfiles.getTotalPages(), allUserProfiles.getTotalElements());
        getUserProfileResponse.setPageResponse(pageResponse);
        return getUserProfileResponse;
    }

    @Override
    public ApiResponse updateUserProfile(UserProfileModel userProfileModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileModel.getId());
        if (userProfile.isPresent()) {
            UserProfile userProfileObj = userProfile.get();
            if (userProfileModel.getEmail() != null) {
                checkIfEmailUnique(userProfileModel.getEmail(), userProfile.get().getId());
            }
            // if (userModel.getUserName() != null) {
            //     checkIfUserNameUnique(userModel.getUserName(), userProfile.get().getId());
            // }
            if (userProfileModel.getFirstName() != null)
                userProfileObj.setFirstName(userProfileModel.getFirstName());
            if (userProfileModel.getLastName() != null)
                userProfileObj.setLastName(userProfileModel.getLastName());
            if (userProfileModel.getEmail() != null)
                userProfileObj.setEmail(userProfileModel.getEmail());
            userProfileRepository.save(userProfileObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteUserProfile(Long userProfileId) throws Exception {
        userProfileRepository.deleteById(userProfileId);
    }

    @Override
    public UserProfile getUserProfile(Long id) throws Exception {
        System.out.println(id);
        return userProfileRepository.findById(id).orElseThrow(() -> new Exception("Invalid UserProfileId"));
    }
}
