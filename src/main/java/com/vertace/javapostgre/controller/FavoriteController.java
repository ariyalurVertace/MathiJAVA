package com.vertace.javapostgre.controller;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.FavoriteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public FavoriteModel createFavorite(@RequestBody FavoriteModel favoriteModel) throws Exception {
        try {
            return favoriteService.createFavorite(favoriteModel);
        } catch (Exception e) {
            throw e;
        }
    }}
    

