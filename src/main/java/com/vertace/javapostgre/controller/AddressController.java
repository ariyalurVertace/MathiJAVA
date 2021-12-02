package com.vertace.javapostgre.controller;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.AddressService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressModel createAddress(@RequestBody AddressModel addressModel) throws Exception {
        try {
            return addressService.createAddress(addressModel);
        } catch (Exception e) {
            throw e;
        }
    }}
    

