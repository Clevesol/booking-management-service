package com.enactor.appcore.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseSingleton {

    // 1. A thread-safe map to hold instances for each specific class
    private static final Map<Class<?>, BaseSingleton> INSTANCES = new ConcurrentHashMap<>();

    // 2. The common logic to get or create the instance
    @SuppressWarnings("unchecked")
    protected static <T extends BaseSingleton> T getInstance(Class<T> clazz) {
        return (T) INSTANCES.computeIfAbsent(clazz, k -> {
            try {

                // Requires a protected/public no-arg constructor in children
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create singleton for " + clazz.getName(), e);
            }
        });
    }

}