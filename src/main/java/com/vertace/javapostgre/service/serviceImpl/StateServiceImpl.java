package com.vertace.javapostgre.service.serviceImpl;

import com.vertace.javapostgre.commons.ModelMapperUtil;
import com.vertace.javapostgre.entity.State;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.repository.StateRepository;
import com.vertace.javapostgre.service.StateService;
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
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final ModelMapperUtil modelMapperUtil = ModelMapperUtil.getInstance();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public StateModel createState(StateModel stateModel) throws Exception {
        checkIfUserNameUnique(stateModel.getName(), -99L);
        State state = modelMapperUtil.convertToObject(stateModel, State.class);
        stateRepository.save(state);
        //emailService.triggerCreateStateMail(state);
        return modelMapperUtil.convertToObject(state, StateModel.class);
    }

    private void checkIfUserNameUnique(String userName, Long userId) throws UserNameAlreadyExistException {
        State userByStateName = stateRepository.findByName(userName);
        if (userByStateName != null && !userByStateName.getId().equals(userId)) {
            throw new UserNameAlreadyExistException("State Name already exist");
        }
    }


    @Override
    public GenericDataPagedResponse listStates(CustomPageable pageable) {
        Pageable pageAble = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        System.out.println(pageAble);

        Page<State> allStates = stateRepository.findAll(pageAble);
        System.out.println(allStates);
        GenericDataPagedResponse getStateResponse = new GenericDataPagedResponse();
        List<StateModel> userModels = modelMapperUtil.convertToList(allStates.getContent(), StateModel.class);
        if (!userModels.isEmpty())
            getStateResponse.setData(userModels);
        PageResponse pageResponse = new PageResponse(allStates.getTotalPages(), allStates.getTotalElements());
        getStateResponse.setPageResponse(pageResponse);
        return getStateResponse;
    }

    @Override
    public ApiResponse updateState(StateModel stateModel) throws UserNameAlreadyExistException {
        Optional<State> state = stateRepository.findById(stateModel.getId());
        if (state.isPresent()) {
            State userObj = state.get();
            if (stateModel.getName() != null) {
                checkIfUserNameUnique(stateModel.getName(), state.get().getId());
            }
            if (stateModel.getIsDeleted() != null) {
                userObj.setIsDeleted(stateModel.getIsDeleted());
            }
            stateRepository.save(userObj);
        }
        return new ApiResponse(ApiResponse.Status.SUCCESS.toString());
    }

    @Override
    public void deleteState(Long userId) throws Exception {
        stateRepository.deleteById(userId);
    }

    @Override
    public State getState(Long id) throws Exception {
        System.out.println(id);
        return stateRepository.findById(id).orElseThrow(() -> new Exception("Invalid StateId"));
    }
}
