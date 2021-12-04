package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.SellerProfileNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.SellerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/sellerProfile")
@RequiredArgsConstructor
public class SellerProfileController {

    private final SellerProfileService SellerProfileService;

    @PostMapping
    public SellerProfileModel createSellerProfile(@RequestBody SellerProfileModel SellerModel) throws Exception {
        try {
            return SellerProfileService.createSellerProfile(SellerModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getSellerProfile(@RequestBody CustomPageable customPageable) {
        return SellerProfileService.listSellerProfiles(customPageable);
    }

    @PutMapping
    public ApiResponse updateSellerProfile(@RequestBody SellerProfileModel SellerModel) throws Exception {
        return SellerProfileService.updateSellerProfile(SellerModel);
    }

    @DeleteMapping
    public void deleteSellerProfile(@RequestParam("SellerId") Long SellerId) throws Exception {
        SellerProfileService.deleteSellerProfile(SellerId);
    }
    @GetMapping
    public ResponseEntity<?> getSellerProfile(@RequestParam("SellerId") Long SellerId) throws Exception {
        return ResponseEntity.ok(SellerProfileService.getSellerProfile(SellerId));
    }

}
