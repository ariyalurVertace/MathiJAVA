package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.User;
import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.InvalidUserException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface UserService {
    UserModel createUser(UserModel userModel) throws Exception;

    GenericDataPagedResponse listUsers(CustomPageable pageable);

    ApiResponse updateUser(UserModel userModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException;

    void deleteUser(Long userId) throws Exception;

    User getUser(Long userId) throws Exception;

}
