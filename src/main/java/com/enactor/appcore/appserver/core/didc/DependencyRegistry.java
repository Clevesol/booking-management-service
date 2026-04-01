package com.enactor.appcore.appserver.core.didc;

import com.enactor.appcore.appserver.core.didc.filter.DependencyFilter;
import com.enactor.appcore.util.BaseSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.*;

public class DependencyRegistry extends BaseSingleton {

    Logger logger = LoggerFactory.getLogger(DependencyRegistry.class);

    private final Map<Class<?>, Object> componentRegistry = new HashMap<>();

    private final List<DependencyFilter> filters = new ArrayList<>();

    public void registerDependencies(Set<Class<?>> classes) throws Exception {
        if (classes == null || classes.isEmpty()) return;

        Set<Class<?>> remaining = new HashSet<>(classes);
        boolean progressed = true;

        while (!remaining.isEmpty() && progressed) {
            progressed = false;
            Iterator<Class<?>> iterator = remaining.iterator();

            while (iterator.hasNext()) {
                Class<?> clazz = iterator.next();
                Constructor<?> bestConstructor = findSatisfiableConstructor(clazz);

                if (bestConstructor != null) {
                    Object[] parameters = getMatchingDependencies(bestConstructor);
                    Object obj = (parameters.length == 0)
                            ? bestConstructor.newInstance()
                            : bestConstructor.newInstance(parameters);

                    componentRegistry.put(clazz, obj);
                    logger.info(" looping dependency class -> {}", clazz);
                    for(DependencyFilter filter : filters){
                        logger.info("filtering class -> {}", clazz);
                        filter.performFilter(clazz,obj);
                    }


                    iterator.remove();
                    progressed = true;
                }
            }
        }

        if (!remaining.isEmpty()) {
            throw new Exception("Could not resolve dependencies for: " + remaining);
        }

    }

    /**
     * Finds a constructor where all parameter types already exist in the registry.
     * Favors zero-arg constructors first as per your requirement.
     */
    private Constructor<?> findSatisfiableConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Sort to check zero-arg constructors first
        Arrays.sort(constructors, Comparator.comparingInt(Constructor::getParameterCount));

        for (Constructor<?> constructor : constructors) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            boolean allParamsMet = true;

            for (Class<?> paramType : paramTypes) {
                if (!componentRegistry.containsKey(paramType)) {
                    allParamsMet = false;
                    break;
                }
            }

            if (allParamsMet) return constructor;
        }
        return null;
    }

    /**
     * Maps the constructor parameters to instances already in the registry.
     */
    private Object[] getMatchingDependencies(Constructor<?> constructor) {
        return Arrays.stream(constructor.getParameterTypes())
                .map(componentRegistry::get)
                .toArray();
    }

    protected Map<Class<?>, Object> getComponentRegistry() {
        return componentRegistry;
    }

    public void addFilter(DependencyFilter filter){
        this.filters.add(filter);
    }

    public void addFilters(List<DependencyFilter> filters){
        this.filters.addAll(filters);
    }

    public static DependencyRegistry getInstance(){
        return BaseSingleton.getInstance(DependencyRegistry.class);
    }
}
