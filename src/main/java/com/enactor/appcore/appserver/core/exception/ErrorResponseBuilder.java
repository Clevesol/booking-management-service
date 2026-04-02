package com.enactor.appcore.appserver.core.exception;

import com.enactor.appcore.appserver.core.entity.ErrorResponse;

public class ErrorResponseBuilder {


    public static ErrorResponse build(Exception ex, String path) {

        if (ex instanceof AppException) {
            AppException appEx = (AppException) ex;
            return new ErrorResponse(
                    appEx.getErrorCode().getCode(),
                    appEx.getMessage()
            );
        }

        // fallback (unknown errors)
        return new ErrorResponse(
                ErrorCode.INTERNAL_ERROR.getCode(),
                ErrorCode.INTERNAL_ERROR.getDefaultMessage()
        );
    }

}