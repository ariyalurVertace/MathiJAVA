package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Favorite;
import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.FavoriteRepository;
import com.vertace.javapostgre.repository.ProductRepository;
import com.vertace.javapostgre.service.FavoriteService;
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
public class FavoriteServiceImpl implements FavoriteService{
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public FavoriteModel createFavorite(FavoriteModel favoriteModel) throws Exception {
        Long productId = favoriteModel.getProduct().getId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Invalid ProductId"));
        Favorite favorite = modelMapperUtil.convertToObject(favoriteModel, Favorite.class);
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
        //emailService.triggerCreateAddressMail(address);
        return modelMapperUtil.convertToObject(favorite, FavoriteModel.class);
    }

    @Override
    public GenericDataPagedResponse listFavorites(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Favorite> allFavorites = favoriteRepository.findAll(pageAble);
        System.out.println(allFavorites);
        GenericDataPagedResponse getFavoriteResponse = new GenericDataPagedResponse();
        System.out.println(allFavorites.getContent());
        List<FavoriteModel> userModels = modelMapperUtil.convertToList(allFavorites.getContent(), FavoriteModel.class);
        if (!userModels.isEmpty())
            getFavoriteResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allFavorites.getTotalPages(), allFavorites.getTotalElements());
        getFavoriteResponse.setPageResponse(pageResponse);
        return getFavoriteResponse;
    }

    @Override
    public ApiResponse updateFavorite(FavoriteModel favoriteModel){
        Optional<Favorite> favorite = favoriteRepository.findById(favoriteModel.getId());
        if (favorite.isPresent()) {
            Favorite userObj = favorite.get();
            if (favoriteModel.getIsDeleted() != null) {
                userObj.setIsDeleted(favoriteModel.getIsDeleted());
            }
            if (favoriteModel.getProduct() != null) {
                userObj.setProduct(favoriteModel.getProduct());
            }
            favoriteRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteFavorite(Long userId) throws Exception {
        favoriteRepository.deleteById(userId);
    }

    @Override
    public Favorite getFavorite(Long id) throws Exception {
        System.out.println(id);
        return favoriteRepository.findById(id).orElseThrow(() -> new Exception("Invalid FavoriteId"));
    }

}
