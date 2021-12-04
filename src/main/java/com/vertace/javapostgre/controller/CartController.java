package com.vertace.javapostgre.controller;


import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    
    @PostMapping
    public CartModel createCart(@RequestBody CartModel cartModel) throws Exception {
        try {
            return cartService.createCart(cartModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getCart(@RequestBody CustomPageable customPageable) {
        return cartService.listCarts(customPageable);} 

        @PutMapping
    public ApiResponse updateCart(@RequestBody CartModel cartModel) throws Exception {
        return cartService.updateCart(cartModel);
    }

    @DeleteMapping
    public void deleteCart(@RequestParam("id") Long id) throws Exception {
        cartService.deleteCart(id);
    }
    @GetMapping
    public ResponseEntity<?> getCart(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(cartService.getCart(id));
    }
    }