package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Order;
import com.vertace.javapostgre.model.*;
public interface OrderService {
    OrderModel createOrder(OrderModel orderModel) throws Exception;

    GenericDataPagedResponse listOrders(CustomPageable pageable);

    ApiResponse updateOrder(OrderModel orderModel) throws Exception;

    void deleteOrder(Long id) throws Exception;

    Order getOrder(Long id) throws Exception;
}