package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.SellerProfile;
import com.vertace.javapostgre.entity.Address;
import com.vertace.javapostgre.exceptions.SellerProfileNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.SellerProfileRepository;
import com.vertace.javapostgre.repository.AddressRepository;
import com.vertace.javapostgre.service.SellerProfileService;
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

public class SellerProfileServiceImpl implements SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;
    private final AddressRepository addressRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public SellerProfileModel createSellerProfile(SellerProfileModel SellerProfileModel) throws Exception {
       checkIfEmailUnique(SellerProfileModel.getEmail(), -99L);
        checkIfSellerNameUnique(SellerProfileModel.getEmail(), -99L);
        SellerProfile seller = modelMapperUtil.convertToObject(SellerProfileModel, SellerProfile.class);
        sellerProfileRepository.save(seller);
        //emailService.triggerCreateUserProfileMail(user);
        return modelMapperUtil.convertToObject(SellerProfileModel, SellerProfileModel.class);
    }
    
    private void checkIfEmailUnique(String email, long l) {
	}

	private void checkIfSellerNameUnique(String sellerName, Long sellerId)
            throws SellerProfileNameAlreadyExistException {
        SellerProfile userBySellerProfileName = sellerProfileRepository.findBySellerProfileName(sellerName);
        if (userBySellerProfileName != null && !userBySellerProfileName.getId().equals(sellerId)) {
            throw new SellerProfileNameAlreadyExistException("Sellerprofile Name already exist");
        }
    }

    @Override
    public GenericDataPagedResponse listSellerProfiles(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<SellerProfile> allSellerProfiles = sellerProfileRepository.findAll(pageAble);
        System.out.println(allSellerProfiles);
        GenericDataPagedResponse getSellerProfileResponse = new GenericDataPagedResponse();
        System.out.println(allSellerProfiles.getContent());
        List<SellerProfileModel> sellerProfileModels = modelMapperUtil.convertToList(allSellerProfiles.getContent(), SellerProfileModel.class);
        if (!sellerProfileModels.isEmpty())
            getSellerProfileResponse.setData(sellerProfileModels);
        PageResponse pageResponse = new PageResponse(allSellerProfiles.getTotalPages(), allSellerProfiles.getTotalElements());
        getSellerProfileResponse.setPageResponse(pageResponse);
        return getSellerProfileResponse;
    }

    @Override
    public ApiResponse updateSellerProfile(SellerProfileModel sellerProfileModel) throws SellerProfileNameAlreadyExistException {
        Optional<SellerProfile> sellerProfile = sellerProfileRepository.findById(sellerProfileModel.getId());
        if (sellerProfile.isPresent()) {
            SellerProfile sellerObj = sellerProfile.get();
            if (sellerProfileModel.getFirstName() != null) {
                checkIfSellerNameUnique(sellerProfileModel.getFirstName(), sellerProfile.get().getId());
            }
            if (sellerProfileModel.getIsDeleted() != null) {
                sellerObj.setIsDeleted(sellerProfileModel.getIsDeleted());
            }
            sellerProfileRepository.save(sellerObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteSellerProfile(Long sellerId) throws Exception {
        sellerProfileRepository.deleteById(sellerId);
    }

    @Override
    public SellerProfile getSellerProfile(Long id) throws Exception {
        System.out.println(id);
        return sellerProfileRepository.findById(id).orElseThrow(() -> new Exception("Invalid SellerProfileId"));
    }
}