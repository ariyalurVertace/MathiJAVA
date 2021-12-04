package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Order;
import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.OrderRepository;
import com.vertace.javapostgre.repository.ProductRepository;
import com.vertace.javapostgre.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private static final Product Product = null;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public OrderModel createOrder(OrderModel orderModel) throws Exception {
        Long productId = orderModel.getProduct().getId() -99L;
        Order order = modelMapperUtil.convertToObject(orderModel, Order.class);
        order.setProduct(Product);
        orderRepository.save(order);
        //emailService.triggerCreateAddressMail(address);
        return modelMapperUtil.convertToObject(order,OrderModel.class);
    }

    @Override
    public GenericDataPagedResponse listOrders(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Order> allOrders = orderRepository.findAll(pageAble);
        System.out.println(allOrders);
        GenericDataPagedResponse getOrderResponse = new GenericDataPagedResponse();
        System.out.println(allOrders.getContent());
        List<OrderModel> userModels = modelMapperUtil.convertToList(allOrders.getContent(), OrderModel.class);
        if (!userModels.isEmpty())
            getOrderResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allOrders.getTotalPages(), allOrders.getTotalElements());
        getOrderResponse.setPageResponse(pageResponse);
        return getOrderResponse;
    }

    @Override
    public ApiResponse updateOrder(OrderModel orderModel) throws Exception {
        Optional<Order> order = orderRepository.findById(orderModel.getId());
        if (order.isPresent()) {
            Order userObj = order.get();
            if (orderModel.getIsDeleted() != null) {
                userObj.setIsDeleted(orderModel.getIsDeleted());
            }
            if (orderModel.getProduct() != null) {
                userObj.setProduct(orderModel.getProduct());
            }
            orderRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteOrder(Long userId) throws Exception {
        orderRepository.deleteById(userId);
    }

    @Override
    public Order getOrder(Long id) throws Exception {
        System.out.println(id);
        return orderRepository.findById(id).orElseThrow(() -> new Exception("Invalid OrderId"));
    }

}
