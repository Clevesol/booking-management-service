package com.enactor.appserver.core;

import java.util.Map;
import java.util.Set;

public class DependencyManager {

    private final ControllerDependencyFilteredRegistry dependencyRegistry;

    public DependencyManager(){
        this.dependencyRegistry = ControllerDependencyFilteredRegistry.getInstance();
    }

    public void init(Set<Class<?>> classes){
        try {
            this.dependencyRegistry.registerDependencies(classes);
            this.wireDependencies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void wireDependencies(){
        Map<Class<?>, Object> componentRegistry = this.dependencyRegistry.getComponentRegistry();

    }

    private static DependencyManager dependencyManager;

    public static DependencyManager getInstance(){
        if(null == dependencyManager) dependencyManager = new DependencyManager();
        return dependencyManager;
    }
}
