package com.vertace.javapostgre.commons;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperUtil {

    private static ModelMapperUtil modelMapperUtil = null;
    private ModelMapper modelMapper = null;

    private ModelMapperUtil() {
        modelMapper = new ModelMapper();
    }

    public static ModelMapperUtil getInstance() {
        if (modelMapperUtil == null) {
            modelMapperUtil = new ModelMapperUtil();
        }
        return modelMapperUtil;
    }

    public <T> T convertToObject(Object obj, Class<T> classOfT) {
        return modelMapper.map(obj, classOfT);
    }

    public <S, T> List<T> convertToList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }


}
