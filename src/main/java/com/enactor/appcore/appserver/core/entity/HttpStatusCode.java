package com.enactor.appcore.appserver.core.entity;

public enum HttpStatusCode {

    OK(200),
    ACCEPTED(201),
    INTERNAL_ERROR(500),
    NOT_FOUND(404),
    BAD_REQUEST(400),
    UNAUTHORIZED(401);
    private final int code;

    HttpStatusCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
