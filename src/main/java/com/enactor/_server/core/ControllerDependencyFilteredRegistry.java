package com.enactor._server.core;

import com.enactor._server.core.annotation.Controller;
import com.enactor._server.core.annotation.RequestMapping;
import com.enactor._server.interceptor.ControllerMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControllerDependencyFilteredRegistry extends DependencyRegistry{

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

    private static ControllerDependencyFilteredRegistry dependencyRegistry;

    public static ControllerDependencyFilteredRegistry getInstance(){
        if(null == dependencyRegistry) dependencyRegistry = new ControllerDependencyFilteredRegistry();
        return dependencyRegistry;
    }
}
