package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.UserProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/userProfile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public UserProfileModel createUserProfile(@RequestBody UserProfileModel userProfileModel) throws Exception {
        try {
            return userProfileService.createUserProfile(userProfileModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getUserProfile(@RequestBody CustomPageable customPageable) {
        return userProfileService.listUserProfiles(customPageable);
    }

    @PutMapping
    public ApiResponse updateUser(@RequestBody UserProfileModel userProfileModel) throws EmailIdAlreadyExistException, UserNameAlreadyExistException {
        return userProfileService.updateUserProfile(userProfileModel);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("userId") Long userId) throws Exception {
        userProfileService.deleteUserProfile(userId);
    }
    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(userProfileService.getUserProfile(id));
    }

}
