package com.enactor.appcore.appserver.core.exception;

public enum ErrorCode {

    INTERNAL_ERROR("ERR-500", "Internal server error"),
    NOT_FOUND("ERR-404", "Resource not found"),
    VALIDATION_ERROR("ERR-400", "Validation failed"),
    UNAUTHORIZED("ERR-401", "Unauthorized");

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}