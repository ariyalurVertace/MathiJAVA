package com.vertace.javapostgre.service;

import com.vertace.javapostgre.entity.State;
import com.vertace.javapostgre.exceptions.UserNameAlreadyExistException;
import com.vertace.javapostgre.model.*;

public interface StateService {
    StateModel createState(StateModel stateModel) throws Exception;

    GenericDataPagedResponse listStates(CustomPageable pageable);

    ApiResponse updateState(StateModel stateModel) throws UserNameAlreadyExistException;

    void deleteState(Long id) throws Exception;

    State getState(Long id) throws Exception;

}
