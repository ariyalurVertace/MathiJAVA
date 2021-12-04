package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.Banner;

import com.vertace.javapostgre.model.*;

public interface BannerService {
    BannerModel createBanner(BannerModel BannerModel) throws Exception;

    GenericDataPagedResponse listBanners(CustomPageable pageable);

    ApiResponse updateBanner(BannerModel BannerModel) throws Exception;

    void deleteBanner(Long id) throws Exception;

    Banner getBanner(Long id) throws Exception;

}
