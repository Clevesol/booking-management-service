package com.enactor._server.interceptor;

import com.enactor._server.core.ControllerDependencyFilteredRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class RequestInterceptor {

    private final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private final ControllerDependencyFilteredRegistry filteredRegistry;

    public RequestInterceptor(){
        this.filteredRegistry = ControllerDependencyFilteredRegistry.getInstance();
    }

    public void intercept(final String path, final String method, final HttpServletRequest req, final HttpServletResponse resp) throws Exception{
        final String controllerIdx = method.concat(":").concat(path);
        log.warn("looking for controller {}", controllerIdx);
        this.filteredRegistry.getControllerMethod(controllerIdx).invoke(req,resp);
    }
}
