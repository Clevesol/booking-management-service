package com.enactor.appserver.core;

import com.enactor.appserver.core.annotation.Controller;
import com.enactor.appserver.core.annotation.RequestMapping;
import com.enactor.appserver.interceptor.ControllerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControllerDependencyFilteredRegistry extends DependencyRegistry{

    private static ControllerDependencyFilteredRegistry dependencyRegistry;
    private final Logger log = LoggerFactory.getLogger(ControllerDependencyFilteredRegistry.class);
    private final Map<String, ControllerMethod> controllerMappings = new HashMap<>();

    /**
     * @param clazz
     */
    @Override
    protected void performFilter(Class clazz, Object obj) {

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
        if(null == dependencyRegistry) dependencyRegistry = new ControllerDependencyFilteredRegistry();
        return dependencyRegistry;
    }
}
