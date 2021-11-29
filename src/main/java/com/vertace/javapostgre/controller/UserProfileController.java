package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserProfileNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userService;

    @PostMapping
    public UserProfileModel createUserProfile(@RequestBody UserProfileModel userModel) throws Exception {
        try {
            return userService.createUserProfile(userModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getUserProfile(@RequestBody CustomPageable customPageable) {
        return userService.listUserProfiles(customPageable);
    }

    @PutMapping
    public ApiResponse updateUserProfile(@RequestBody UserProfileModel userModel) throws EmailIdAlreadyExistException, UserProfileNameAlreadyExistException {
        return userService.updateUserProfile(userModel);
    }

    @DeleteMapping
    public void deleteUserProfile(@RequestParam("userId") Long userId) throws Exception {
        userService.deleteUserProfile(userId);
    }
    @GetMapping
    public ResponseEntity<?> getUserProfile(@RequestParam("userId") Long userId) throws Exception {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

}
