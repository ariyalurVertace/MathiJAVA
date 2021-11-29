package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.District;
import com.vertace.javapostgre.entity.State;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.DistrictRepository;
import com.vertace.javapostgre.repository.StateRepository;
import com.vertace.javapostgre.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public DistrictModel createDistrict(DistrictModel districtModel) throws Exception {
        checkIfUserNameUnique(districtModel.getName(), -99L);
        Long stateId = districtModel.getState().getId();
        State state = stateRepository.findById(stateId).orElseThrow(() -> new Exception("Invalid StateId"));
        District district = modelMapperUtil.convertToObject(districtModel, District.class);
        district.setState(state);
        districtRepository.save(district);
        //emailService.triggerCreateDistrictMail(district);
        return modelMapperUtil.convertToObject(district, DistrictModel.class);
    }

    private void checkIfUserNameUnique(String userName, Long userId) throws UserNameAlreadyExistException {
        District userByDistrictName = districtRepository.findByName(userName);
        if (userByDistrictName != null && !userByDistrictName.getId().equals(userId)) {
            throw new UserNameAlreadyExistException("District Name already exist");
        }
    }


    @Override
    public GenericDataPagedResponse listDistricts(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<District> allDistricts = districtRepository.findAll(pageAble);
        System.out.println(allDistricts);
        GenericDataPagedResponse getDistrictResponse = new GenericDataPagedResponse();
        System.out.println(allDistricts.getContent());
        List<DistrictModel> userModels = modelMapperUtil.convertToList(allDistricts.getContent(), DistrictModel.class);
        if (!userModels.isEmpty())
            getDistrictResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allDistricts.getTotalPages(), allDistricts.getTotalElements());
        getDistrictResponse.setPageResponse(pageResponse);
        return getDistrictResponse;
    }

    @Override
    public ApiResponse updateDistrict(DistrictModel districtModel) throws UserNameAlreadyExistException {
        Optional<District> district = districtRepository.findById(districtModel.getId());
        if (district.isPresent()) {
            District userObj = district.get();
            if (districtModel.getName() != null) {
                checkIfUserNameUnique(districtModel.getName(), district.get().getId());
            }
            if (districtModel.getIsDeleted() != null) {
                userObj.setIsDeleted(districtModel.getIsDeleted());
            }
            if (districtModel.getState() != null) {
                userObj.setState(districtModel.getState());
            }
            districtRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteDistrict(Long userId) throws Exception {
        districtRepository.deleteById(userId);
    }

    @Override
    public District getDistrict(Long id) throws Exception {
        System.out.println(id);
        return districtRepository.findById(id).orElseThrow(() -> new Exception("Invalid DistrictId"));
    }
}
