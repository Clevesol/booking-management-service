package com.enactor.appcore.appserver.interceptor;

import com.enactor.appcore.appserver.core.didc.DependencyManager;
import com.enactor.appcore.appserver.core.didc.filter.impl.ControllerDependencyFilteredRegistry;
import com.enactor.appcore.appserver.core.entity.ErrorResponse;
import com.enactor.appcore.appserver.core.entity.ResponseEntity;
import com.enactor.appcore.appserver.core.exception.ErrorResponseBuilder;
import com.enactor.appcore.util.JSONMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestInterceptor {


    private final DependencyManager dependencyManager = DependencyManager.getInstance();

    private final ControllerDependencyFilteredRegistry filteredRegistry;

    private final JSONMapper jsonMapper = JSONMapper.getInstance();

    private final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    public RequestInterceptor() {
        this.filteredRegistry = (ControllerDependencyFilteredRegistry) dependencyManager.getDependencyFilter(ControllerHandler.class);
    }

    public void intercept(final String path, final String httpMethod, final HttpServletRequest req, final HttpServletResponse resp) throws Exception {

        try {
            String routeKey = buildRouteKey(httpMethod, path);

            ControllerHandler handler = filteredRegistry.getFilterCriteria(routeKey);

            if (handler == null) {
                handleNotFound(resp);
                return;
            }

            ResponseEntity response = execute(handler, req, resp);

            writeResponse(resp, response);

        } catch (Exception ex) {
            handleException(resp, path, ex);
        }

    }

    private String buildRouteKey(String method, String path) {
        return method + ":" + path;
    }

    private ResponseEntity execute(
            ControllerHandler handler,
            HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        return getResponseEntity(handler, req, resp);
    }

    private void writeResponse(HttpServletResponse resp, ResponseEntity response) throws Exception {
        resp.setStatus(response.getStatus());
        resp.setContentType("application/json");
        jsonMapper.writeToStream(resp.getOutputStream(), response.getBody());
    }

    private void handleException(final HttpServletResponse resp, final String path, final Exception ex) {
        try {
            logger.error("handling exception resource path : {} exception : {}",path, ex.getStackTrace());

            ErrorResponse response = ErrorResponseBuilder.build(ex, path);

            writeResponse(resp, new ResponseEntity(response).error(Integer.parseInt(response.getErrorCode())));
        } catch (Exception exception) {
            logger.error("Error while sending error response to the client {}, caused by {}", ex.getStackTrace(), exception.getStackTrace());
        }
    }

    private void handleNotFound(HttpServletResponse resp) throws Exception {

        ResponseEntity response = new ResponseEntity().notFound();
        writeResponse(resp, response);
    }

    private ResponseEntity getResponseEntity(ControllerHandler handler, HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, UnsupportedOperationException {

        if(handler == null) throw new UnsupportedOperationException("The handler dependency unsatisfied");

        ResponseEntity response;
        Method method = handler.getMethod();

        Class<?> returnType = method.getReturnType();

        if (returnType == void.class) {
            throw new UnsupportedOperationException("The method type void is not supported for the type controller at the moment");
        }


        Object result = handler.invoke(req, resp);
        if (returnType != ResponseEntity.class) {
            response = new ResponseEntity(result).ok();

        } else {
            response = (ResponseEntity) result;
        }

        return response;
    }
}
