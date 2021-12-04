package com.vertace.javapostgre.controller;

import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderModel createOrder(@RequestBody OrderModel orderModel) throws Exception {
        try {
            return orderService.createOrder(orderModel);
        } catch (Exception e) {
            throw e;
        }
    }
}
    

