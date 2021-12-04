package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Cart;

import com.vertace.javapostgre.model.*;

public interface CartService {
    CartModel createCart(CartModel cartModel) throws Exception;

    GenericDataPagedResponse listCarts(CustomPageable pageable);

    ApiResponse updateCart(CartModel cartModel) throws Exception;

    void deleteCart(Long id) throws Exception;

    Cart getCart(Long id) throws Exception;

}
