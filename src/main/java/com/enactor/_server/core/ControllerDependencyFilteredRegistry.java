package com.enactor._server.core;

import com.enactor._server.core.annotation.Controller;
import com.enactor._server.core.annotation.RequestMapping;
import com.enactor._server.interceptor.ControllerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControllerDependencyFilteredRegistry extends DependencyRegistry{

    private final Logger log = LoggerFactory.getLogger(ControllerDependencyFilteredRegistry.class);
    private final Map<String, ControllerMethod> controllerMappings = new HashMap<>();

    /**
     * @param clazz
     */
    @Override
    protected void performFilter(Class clazz, Object obj) {
        log.info("filtering {} {}", clazz, obj);
        if (clazz.isAnnotationPresent(Controller.class)) {
            log.info("annotation presents {} {}", clazz, obj);
            Arrays.stream(clazz.getDeclaredMethods()).forEach(
                    method -> {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            log.info("adding controller {}", method.getName());
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mappingId = requestMapping.method()+":"+ requestMapping.path();
                            log.info("adding mappingId {}", mappingId);
                            this.controllerMappings.put(mappingId, new ControllerMethod(obj, method));
                            log.info("controller mappings :: {}",this.controllerMappings);
                        }
                    }
            );
        }
    }

    public ControllerMethod getControllerMethod(String identifier){
        log.info(" get controller method {} {}",controllerMappings.entrySet().toArray(), controllerMappings);
        return this.controllerMappings.get(identifier);
    }

    private static ControllerDependencyFilteredRegistry dependencyRegistry;

    public static ControllerDependencyFilteredRegistry getInstance(){
        if(null == dependencyRegistry) dependencyRegistry = new ControllerDependencyFilteredRegistry();
        return dependencyRegistry;
    }
}
