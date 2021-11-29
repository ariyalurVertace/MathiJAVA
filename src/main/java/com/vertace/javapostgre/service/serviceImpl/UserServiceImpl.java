package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.UserProfile;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.UserRepository;
import com.vertace.javapostgre.service.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserModel createUser(UserModel userModel) throws Exception {
        checkIfEmailUnique(userModel.getEmail(), -99L);
        checkIfUserNameUnique(userModel.getEmail(), -99L);
        if (userModel.getPassword() == null || userModel.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }
        UserProfile userProfile = modelMapperUtil.convertToObject(userModel, UserProfile.class);
        userProfile.setPassword(userModel.getPassword());
        userRepository.save(userProfile);
        //emailService.triggerCreateUserMail(userProfile);
        return modelMapperUtil.convertToObject(userProfile, UserModel.class);
    }

    private void checkIfUserNameUnique(String userName, Long userId) throws UserNameAlreadyExistException {
        UserProfile userByUserName = userRepository.findByUserName(userName);
        if (userByUserName != null && !userByUserName.getId().equals(userId)) {
            throw new UserNameAlreadyExistException("UserProfile Name already exist");
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
    public GenericDataPagedResponse listUsers(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<UserProfile> allUsers = userRepository.findAll(pageAble);
        System.out.println(allUsers);
        GenericDataPagedResponse getUserResponse = new GenericDataPagedResponse();
        List<UserModel> userModels = modelMapperUtil.convertToList(allUsers.getContent(), UserModel.class);
        if (!userModels.isEmpty())
            getUserResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allUsers.getTotalPages(), allUsers.getTotalElements());
        getUserResponse.setPageResponse(pageResponse);
        return getUserResponse;
    }

    @Override
    public ApiResponse updateUser(UserModel userModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException {
        Optional<UserProfile> userProfile = userRepository.findById(userModel.getId());
        if (userProfile.isPresent()) {
            UserProfile userObj = userProfile.get();
            if (userModel.getEmail() != null) {
                checkIfEmailUnique(userModel.getEmail(), userProfile.get().getId());
            }
            // if (userModel.getUserName() != null) {
            //     checkIfUserNameUnique(userModel.getUserName(), userProfile.get().getId());
            // }
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
    public void deleteUser(Long userId) throws Exception {
        userRepository.deleteById(userId);
    }

    @Override
    public UserProfile getUser(Long id) throws Exception {
        System.out.println(id);
        return userRepository.findById(id).orElseThrow(() -> new Exception("Invalid UserId"));
    }
}
