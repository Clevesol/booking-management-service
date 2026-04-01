package com.enactor.appcore.appserver.interceptor;

import com.enactor.appcore.appserver.core.didc.filter.impl.ControllerDependencyFilteredRegistry;
import com.enactor.appcore.appserver.core.entity.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class RequestInterceptor {

    private final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private final ControllerDependencyFilteredRegistry filteredRegistry;

    public RequestInterceptor(){
        this.filteredRegistry = ControllerDependencyFilteredRegistry.getInstance();
    }

    public void intercept(final String path, final String httpMethod, final HttpServletRequest req, final HttpServletResponse resp) throws Exception{

        ResponseEntity response;
        try {
            final String controllerIdx = httpMethod.concat(":").concat(path);
            ControllerDependencyFilteredRegistry.printTh();
            ControllerMethod controllerMethod = this.filteredRegistry.getControllerMethod(controllerIdx);//.invoke(req,resp);
            response = getResponseEntity(controllerMethod);



        }catch (Exception ex){
            response = new ResponseEntity();
        }



    }

    private ResponseEntity getResponseEntity(ControllerMethod controllerMethod){
        Method method = controllerMethod.getMethod();
        Type genericReturnType = method.getGenericReturnType();
        if(genericReturnType == void.class){

        }else{

        }

        return null;
    }
}
