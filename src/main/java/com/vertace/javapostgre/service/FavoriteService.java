package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Favorite;
import com.vertace.javapostgre.model.*;

 public interface FavoriteService {
    FavoriteModel createFavorite(FavoriteModel favoriteModel) throws Exception;

    GenericDataPagedResponse listFavorites(CustomPageable pageable);

    ApiResponse updateFavorite(FavoriteModel favoriteModel);

    void deleteFavorite(Long id) throws Exception;

    Favorite getFavorite(Long id) throws Exception;

}
