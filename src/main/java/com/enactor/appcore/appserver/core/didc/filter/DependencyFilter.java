package com.enactor.appcore.appserver.core.didc.filter;

public interface DependencyFilter {

    void performFilter(Class clazz, Object obj);
}
