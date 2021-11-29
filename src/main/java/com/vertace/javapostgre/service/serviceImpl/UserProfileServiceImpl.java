package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.UserProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserProfileNameAlreadyExistException;
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

    private final UserProfileRepository userRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserProfileModel createUserProfile(UserProfileModel userProfileModel) throws Exception {
        checkIfEmailUnique(userProfileModel.getEmail(), -99L);
        checkIfUserProfileNameUnique(userProfileModel.getEmail(), -99L);
        UserProfile user = modelMapperUtil.convertToObject(userProfileModel, UserProfile.class);
        userRepository.save(user);
        //emailService.triggerCreateUserProfileMail(user);
        return modelMapperUtil.convertToObject(user, UserProfileModel.class);
    }

    private void checkIfUserProfileNameUnique(String userName, Long userId) throws UserProfileNameAlreadyExistException {
        UserProfile userByUserProfileName = userRepository.findByUserProfileName(userName);
        if (userByUserProfileName != null && !userByUserProfileName.getId().equals(userId)) {
            throw new UserProfileNameAlreadyExistException("UserProfile Name already exist");
        }
    }

    private void checkIfEmailUnique(String email, Long userId) throws EmailIdAlreadyExistException {
        UserProfile userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            return;
        }
        if (!userByEmail.getId().equals(userId)) {
            throw new EmailIdAlreadyExistException("Email id already exist");
        }
    }

    @Override
    public GenericDataPagedResponse listUserProfiles(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<UserProfile> allUserProfiles = userRepository.findAll(pageAble);
        GenericDataPagedResponse getUserProfileResponse = new GenericDataPagedResponse();
        List<UserProfileModel> userModels = modelMapperUtil.convertToList(allUserProfiles.getContent(), UserProfileModel.class);
        if (!userModels.isEmpty())
            getUserProfileResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allUserProfiles.getTotalPages(), allUserProfiles.getTotalElements());
        getUserProfileResponse.setPageResponse(pageResponse);
        return getUserProfileResponse;
    }

    @Override
    public ApiResponse updateUserProfile(UserProfileModel userModel) throws EmailIdAlreadyExistException, UserProfileNameAlreadyExistException {
        Optional<UserProfile> user = userRepository.findById(userModel.getId());
        if (user.isPresent()) {
            UserProfile userObj = user.get();
            if (userModel.getEmail() != null) {
                checkIfEmailUnique(userModel.getEmail(), user.get().getId());
            }
            if (userModel.getUserName() != null) {
                checkIfUserProfileNameUnique(userModel.getUserName(), user.get().getId());
            }
            if (userModel.getFirstName() != null)
                userObj.setFirstName(userModel.getFirstName());
            if (userModel.getLastName() != null)
                userObj.setLastName(userModel.getLastName());
            if (userModel.getEmail() != null)
                userObj.setEmail(userModel.getEmail());
            userRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteUserProfile(Long userId) throws Exception {
        userRepository.deleteById(userId);
    }

    @Override
    public UserProfile getUserProfile(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("Invalid UserProfileId"));
    }
}
