package com.enactor.appcore.appserver.core.didc.filter.impl;

import com.enactor.appcore.appserver.core.annotation.Controller;
import com.enactor.appcore.appserver.core.annotation.RequestMapping;
import com.enactor.appcore.appserver.core.didc.filter.DependencyFilter;
import com.enactor.appcore.appserver.interceptor.ControllerMethod;
import com.enactor.appcore.util.BaseSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControllerDependencyFilteredRegistry extends BaseSingleton implements DependencyFilter {


    private final Map<String, ControllerMethod> controllerMappings = new HashMap<>();

    /**
     * @param clazz
     */
    @Override
    public void performFilter(Class clazz, Object obj) {
        Logger log = LoggerFactory.getLogger(ControllerDependencyFilteredRegistry.class);
        log.info("perform filter -> {}", clazz);
        if (clazz.isAnnotationPresent(Controller.class)) {
            Arrays.stream(clazz.getDeclaredMethods()).forEach(
                    method -> {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mappingId = requestMapping.method()+":"+ requestMapping.path();
                            this.controllerMappings.put(mappingId, new ControllerMethod(obj, method));
                        }
                    }
            );
        }
    }

    public ControllerMethod getControllerMethod(String identifier){
        return this.controllerMappings.get(identifier);
    }



    public static ControllerDependencyFilteredRegistry getInstance(){
        return BaseSingleton.getInstance(ControllerDependencyFilteredRegistry.class);
    }
}
