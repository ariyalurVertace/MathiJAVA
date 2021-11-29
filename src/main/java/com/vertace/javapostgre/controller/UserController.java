package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/userProfile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) throws Exception {
        try {
            return userService.createUser(userModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getUser(@RequestBody CustomPageable customPageable) {
        return userService.listUsers(customPageable);
    }

    @PutMapping
    public ApiResponse updateUser(@RequestBody UserModel userModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException {
        return userService.updateUser(userModel);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("userId") Long userId) throws Exception {
        userService.deleteUser(userId);
    }
    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(userService.getUser(id));
    }

}
