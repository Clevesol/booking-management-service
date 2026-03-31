package com.enactor._server.core;

import java.util.HashMap;
import java.util.Map;

public class DependencyRegistry {

    private final Map<String, Object> registry = new HashMap<>();

    protected void registerDependencies(Class<?> classes) throws Exception{

    }

    protected void registerDependency(Class<?> clazz){

    }

    protected Map<String,Object> getRegistry(){
        return registry;
    }



}
