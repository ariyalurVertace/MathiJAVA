package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.DistrictService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    public DistrictModel createDistrict(@RequestBody DistrictModel districtModel) throws Exception {
        try {
            return districtService.createDistrict(districtModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getDistrict(@RequestBody CustomPageable customPageable) {
        return districtService.listDistricts(customPageable);
    }

    @PutMapping
    public ApiResponse updateDistrict(@RequestBody DistrictModel districtModel) throws UserNameAlreadyExistException {
        return districtService.updateDistrict(districtModel);
    }

    @DeleteMapping
    public void deleteDistrict(@RequestParam("id") Long id) throws Exception {
        districtService.deleteDistrict(id);
    }
    @GetMapping
    public ResponseEntity<?> getDistrict(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(districtService.getDistrict(id));
    }

}
