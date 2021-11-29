package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.exceptions.EmailIdAlreadyExistException;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.StateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/state")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @PostMapping
    public StateModel createState(@RequestBody StateModel stateModel) throws Exception {
        try {
            return stateService.createState(stateModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getState(@RequestBody CustomPageable customPageable) {
        return stateService.listStates(customPageable);
    }

    @PutMapping
    public ApiResponse updateState(@RequestBody StateModel stateModel) throws UserNameAlreadyExistException {
        return stateService.updateState(stateModel);
    }

    @DeleteMapping
    public void deleteState(@RequestParam("id") Long id) throws Exception {
        stateService.deleteState(id);
    }
    @GetMapping
    public ResponseEntity<?> getState(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(stateService.getState(id));
    }

}
