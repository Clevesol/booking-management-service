package com.enactor.appcore.appserver.core.didc.filter;

public interface DependencyFilter<R,T> {

    void performFilter(Class clazz, Object obj);

    R getFilterCriteria(T t);

}
