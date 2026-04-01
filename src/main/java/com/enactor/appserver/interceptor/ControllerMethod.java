package com.enactor.appserver.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public class ControllerMethod {

    private Object controller;
    private Method method;

    public ControllerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public void invoke(HttpServletRequest req, HttpServletResponse res) throws Exception{
        this.method.invoke(this.controller, req, res);
    }
}
