package com.enactor._server.core;

import com.enactor._server.core.annotation.Controller;
import com.enactor._server.core.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class DependencyRegistry {

    private final Map<Class<?>, Object> componentRegistry = new HashMap<>();

    private final Logger log = LoggerFactory.getLogger(DependencyRegistry.class);

    public void registerDependencies(Set<Class<?>> classes) throws Exception {
        if (null != classes) {
            for (Class clazz : classes) {

                    Constructor constructor = clazz.getDeclaredConstructor();
                    if (null != constructor) {
                        log.info(" Constructor name {} ", constructor.getName());
                        Object obj = constructor.newInstance();
                        componentRegistry.put(clazz, obj);
                        performFilter(clazz,obj);
                    }


            }
        }
    }

    protected abstract void performFilter(Class cls, Object obj);


    protected Map<Class<?>, Object> getComponentRegistry() {
        return componentRegistry;
    }


}
