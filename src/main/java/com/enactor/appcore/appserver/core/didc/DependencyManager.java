package com.enactor.appcore.appserver.core.didc;

import com.enactor.appcore.appserver.core.didc.filter.DependencyFilter;
import com.enactor.appcore.appserver.core.didc.filter.impl.ControllerDependencyFilteredRegistry;
import com.enactor.appcore.appserver.core.didc.filter.impl.ValidatorDependencyFilter;
import com.enactor.appcore.appserver.core.exception.AppException;
import com.enactor.appcore.appserver.core.exception.ErrorCode;
import com.enactor.appcore.appserver.interceptor.ControllerHandler;
import com.enactor.appcore.util.BaseSingleton;
import com.enactor.appcore.util.validator.BaseDTOValidator;

import java.util.Set;

public class DependencyManager extends BaseSingleton {

    private final DependencyRegistry dependencyRegistry;

    public DependencyManager(){
        this.dependencyRegistry = DependencyRegistry.getInstance();
    }

    public void init(Set<Class<?>> classes){
        try {
            this.dependencyRegistry.addFilter(ControllerDependencyFilteredRegistry.getInstance());
            this.dependencyRegistry.addFilter(ValidatorDependencyFilter.getInstance());
            this.dependencyRegistry.registerDependencies(classes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DependencyFilter getDependencyFilter(Class<?> type) throws AppException {
        if (type == ControllerHandler.class)  return ControllerDependencyFilteredRegistry.getInstance();
        if (type == BaseDTOValidator.class)  return ValidatorDependencyFilter.getInstance();

        throw new AppException(ErrorCode.INTERNAL_ERROR);
    }


    public static DependencyManager getInstance(){
        return BaseSingleton.getInstance(DependencyManager.class);
    }
}
