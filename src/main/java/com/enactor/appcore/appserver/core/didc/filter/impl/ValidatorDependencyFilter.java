package com.enactor.appcore.appserver.core.didc.filter.impl;

import com.enactor.appcore.appserver.core.annotation.DTOValidator;
import com.enactor.appcore.appserver.core.didc.filter.DependencyFilter;
import com.enactor.appcore.util.BaseSingleton;
import com.enactor.appcore.util.validator.BaseDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorDependencyFilter extends BaseSingleton implements DependencyFilter {

    private final Map<String, List<BaseDTOValidator>> validators = new HashMap<>();

    /**
     * @param clazz
     * @param obj
     */
    @Override
    public void performFilter(Class clazz, Object obj) {

        Logger log = LoggerFactory.getLogger(ValidatorDependencyFilter.class);
        log.info("perform filter -> {}", clazz);
        if(clazz.isAnnotationPresent(DTOValidator.class)){
            if(obj instanceof BaseDTOValidator) {
                Parameter parameter = clazz.getDeclaredMethods()[0].getParameters()[0];
                final String dtoType = parameter.getName();
                log.info("adding type filter -> {}", dtoType);
                List<BaseDTOValidator> validatorsList = validators.get(dtoType);
                if(null == validatorsList) validatorsList = new ArrayList<>();
                validators.put(dtoType, validatorsList);
            }
        }
    }

    public List<BaseDTOValidator> getValidatorsByDTO(Object dto){
        return this.validators.get(dto.getClass().getName());
    }

    public static ValidatorDependencyFilter getInstance(){
        return BaseSingleton.getInstance(ValidatorDependencyFilter.class);
    }
}
