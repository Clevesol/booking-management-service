package com.enactor._server.interceptor;

import com.enactor._server.core.ControllerDependencyFilteredRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestInterceptor {


    private final ControllerDependencyFilteredRegistry filteredRegistry;

    public RequestInterceptor(){
        this.filteredRegistry = new ControllerDependencyFilteredRegistry();
    }

    public void intercept(final String path, final String method, final HttpServletRequest req, final HttpServletResponse resp) throws Exception{
        final String controllerIdx = path.concat(":").concat(method);
        this.filteredRegistry.getControllerMethod(controllerIdx).invoke(req,resp);
    }
}
