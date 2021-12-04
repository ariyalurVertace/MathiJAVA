package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Banner;
import com.vertace.javapostgre.entity.Product;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.BannerRepository;
import com.vertace.javapostgre.repository.ProductRepository;
import com.vertace.javapostgre.service.BannerService;
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
public class BannerServiceImpl implements BannerService {

    private static final Object Banner = null;
    private final BannerRepository bannerRepository;
    private final ProductRepository productRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public BannerModel createBanner(BannerModel BannerModel) throws Exception {
        checkIfBannerNameUnique(BannerModel.getName(), -99L);
        Long ProductId = BannerModel.getProduct().getId();
        Product product =productRepository.findById(ProductId).orElseThrow(() -> new Exception("Invalid ProductId"));
        Banner banner = modelMapperUtil.convertToObject(BannerModel, Banner.class);
        banner.setProduct(product);
        bannerRepository.save(banner);
        //emailService.triggerCreateDistrictMail(district);
        return modelMapperUtil.convertToObject(Banner, BannerModel.class);
    }


    @Override
    public GenericDataPagedResponse listBanners(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Banner> allBanners = bannerRepository.findAll(pageAble);
        System.out.println(allBanners);
        GenericDataPagedResponse getBannerResponse = new GenericDataPagedResponse();
        System.out.println(allBanners.getContent());
        List<BannerModel> userModels = modelMapperUtil.convertToList(allBanners.getContent(), BannerModel.class);
        if (!userModels.isEmpty())
            getBannerResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allBanners.getTotalPages(), allBanners.getTotalElements());
        getBannerResponse.setPageResponse(pageResponse);
        return getBannerResponse;
    }

    @Override
    public ApiResponse updateBanner(BannerModel BannerModel) throws Exception {
        Optional<Banner> Banner = bannerRepository.findById(BannerModel.getId());
        if (Banner.isPresent()) {
            Banner userObj = Banner.get();
            if (BannerModel.getName() != null) {
                checkIfBannerNameUnique(BannerModel.getName(), Banner.get().getId());
            }
            if (BannerModel.getIsDeleted() != null) {
                userObj.setIsDeleted(BannerModel.getIsDeleted());
            }
            if (BannerModel.getProduct() != null) {
                userObj.setProduct(BannerModel.getProduct());
            }
            bannerRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    private void checkIfBannerNameUnique(String name, Long id) {
    }

    @Override
    public void deleteBanner(Long userId) throws Exception {
        bannerRepository.deleteById(userId);
    }

    @Override
    public Banner getBanner(Long id) throws Exception {
        System.out.println(id);
        return bannerRepository.findById(id).orElseThrow(() -> new Exception("Invalid BannerId"));
    }
}
