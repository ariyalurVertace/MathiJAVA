package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Cart;
import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.entity.UserProfile;

import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.CartRepository;
import com.vertace.javapostgre.repository.ProductRepository;
import com.vertace.javapostgre.repository.UserProfileRepository;
import com.vertace.javapostgre.service.CartService;
import com.vertace.javapostgre.service.ProductService;
import com.vertace.javapostgre.service.UserProfileService;
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
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserProfileRepository userProfileRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public CartModel createCart(CartModel cartModel) throws Exception {
        Long productId = cartModel.getProduct().getId();
        Long userProfileId = cartModel.getUserProfile().getId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Invalid ProductId"));
        UserProfile userProfile = userProfileRepository.findById(userProfileId).orElseThrow(() -> new Exception("Invalid UserProfileId"));
        Cart cart = modelMapperUtil.convertToObject(cartModel, Cart.class);
        Cart.setProduct(product);
        Cart.setUserProfile(userProfile);
        cartRepository.save(cart);
        //emailService.triggerCreateAddressMail(address);
        return modelMapperUtil.convertToObject(cart, CartModel.class);
    }
    @Override
    public GenericDataPagedResponse listCarts(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Cart> allCarts = cartRepository.findAll(pageAble);
        System.out.println(allCarts);
        GenericDataPagedResponse getCartResponse = new GenericDataPagedResponse();
        System.out.println(allCarts.getContent());
        List<CartModel> userModels = modelMapperUtil.convertToList(allCarts.getContent(), CartModel.class);
        if (!userModels.isEmpty())
            getCartResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allCarts.getTotalPages(), allCarts.getTotalElements());
        getCartResponse.setPageResponse(pageResponse);
        return getCartResponse;
    }
    @Override
    public ApiResponse updateCart(CartModel cartModel) throws Exception {
        Optional<Cart> cart = cartRepository.findById(cartModel.getId());
        if (cart.isPresent()) {
            Cart userObj = cart.get();
            if (cartModel.getIsDeleted() != null) {
                userObj.setIsDeleted(cartModel.getIsDeleted());
            }
            if (cartModel.getProduct() != null) {
                userObj.setProduct(cartModel.getProduct());
            }
            if (cartModel.getUserProfile() != null) {
                userObj.setUserProfile(cartModel.getUserProfile());
            }
            cartRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }
    @Override
    public void deleteCart(Long userId) throws Exception {
        cartRepository.deleteById(userId);
    }

    @Override
    public Cart getCart(Long id) throws Exception {
        System.out.println(id);
        return cartRepository.findById(id).orElseThrow(() -> new Exception("Invalid CartId"));
    }

}
