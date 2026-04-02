package com.enactor.appcore.appserver.core.didc.filter.impl;

import com.enactor.appcore.appserver.core.annotation.Controller;
import com.enactor.appcore.appserver.core.annotation.RequestMapping;
import com.enactor.appcore.appserver.core.didc.filter.DependencyFilter;
import com.enactor.appcore.appserver.interceptor.ControllerHandler;
import com.enactor.appcore.util.BaseSingleton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControllerDependencyFilteredRegistry extends BaseSingleton implements DependencyFilter<ControllerHandler,String> {


    private final Map<String, ControllerHandler> controllerMappings = new HashMap<>();

    /**
     * @param clazz
     */
    @Override
    public void performFilter(Class clazz, Object obj) {

        if (clazz.isAnnotationPresent(Controller.class)) {
            Arrays.stream(clazz.getDeclaredMethods()).forEach(
                    method -> {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mappingId = requestMapping.method()+":"+ requestMapping.path();
                            this.controllerMappings.put(mappingId, new ControllerHandler(obj, method));
                        }
                    }
            );
        }
    }

    /**
     * @param t
     * @return
     */
    @Override
    public ControllerHandler getFilterCriteria(String t) {
        return this.getControllerMethod(t);
    }


    private ControllerHandler getControllerMethod(String identifier){
        return this.controllerMappings.get(identifier);
    }



    public static ControllerDependencyFilteredRegistry getInstance(){
        return BaseSingleton.getInstance(ControllerDependencyFilteredRegistry.class);
    }
}
