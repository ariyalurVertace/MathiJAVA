package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.Address;
import com.vertace.javapostgre.entity.District;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.AddressRepository;
import com.vertace.javapostgre.repository.DistrictRepository;
import com.vertace.javapostgre.service.AddressService;
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
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public AddressModel createAddress(AddressModel addressModel) throws Exception {
        Long districtId = addressModel.getDistrict().getId();
        District district = districtRepository.findById(districtId).orElseThrow(() -> new Exception("Invalid DistrictId"));
        Address address = modelMapperUtil.convertToObject(addressModel, Address.class);
        address.setDistrict(district);
        addressRepository.save(address);
        //emailService.triggerCreateAddressMail(address);
        return modelMapperUtil.convertToObject(address, AddressModel.class);
    }

    @Override
    public GenericDataPagedResponse listAddresses(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<Address> allAddresses = addressRepository.findAll(pageAble);
        System.out.println(allAddresses);
        GenericDataPagedResponse getAddressResponse = new GenericDataPagedResponse();
        System.out.println(allAddresses.getContent());
        List<AddressModel> userModels = modelMapperUtil.convertToList(allAddresses.getContent(), AddressModel.class);
        if (!userModels.isEmpty())
            getAddressResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allAddresses.getTotalPages(), allAddresses.getTotalElements());
        getAddressResponse.setPageResponse(pageResponse);
        return getAddressResponse;
    }

    @Override
    public ApiResponse updateAddress(AddressModel addressModel) throws Exception {
        Optional<Address> address = addressRepository.findById(addressModel.getId());
        if (address.isPresent()) {
            Address userObj = address.get();
            if (addressModel.getIsDeleted() != null) {
                userObj.setIsDeleted(addressModel.getIsDeleted());
            }
            if (addressModel.getDistrict() != null) {
                userObj.setDistrict(addressModel.getDistrict());
            }
            addressRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteAddress(Long userId) throws Exception {
        addressRepository.deleteById(userId);
    }

    @Override
    public Address getAddress(Long id) throws Exception {
        System.out.println(id);
        return addressRepository.findById(id).orElseThrow(() -> new Exception("Invalid AddressId"));
    }

}
