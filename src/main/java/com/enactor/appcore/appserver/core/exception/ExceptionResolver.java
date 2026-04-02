package com.enactor.appcore.appserver.core.exception;

import com.enactor.appcore.appserver.core.entity.HttpStatusCode;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionResolver {

    public static int resolveHttpStatus(Exception ex) {

        if (ex instanceof AppException) {
            AppException appEx = (AppException) ex;
             switch (appEx.getErrorCode()) {
                 case NOT_FOUND : return HttpStatusCode.NOT_FOUND.getCode();
                case VALIDATION_ERROR : return HttpStatusCode.BAD_REQUEST.getCode();
                case UNAUTHORIZED :  return HttpStatusCode.UNAUTHORIZED.getCode();
                 case INTERNAL_ERROR: return HttpStatusCode.INTERNAL_ERROR.getCode();
                default : return  HttpStatusCode.INTERNAL_ERROR.getCode();
            }
        }

        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }
}
