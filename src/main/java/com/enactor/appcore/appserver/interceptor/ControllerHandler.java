package com.enactor.appcore.appserver.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerHandler {

    private Object controller;
    private Method method;

    public ControllerHandler(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object invoke(HttpServletRequest req, HttpServletResponse res) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(this.controller, req, res);
    }

    protected Method getMethod(){
        return this.method;
    }
}
