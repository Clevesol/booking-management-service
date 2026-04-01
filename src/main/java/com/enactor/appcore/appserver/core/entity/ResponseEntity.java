package com.enactor.appcore.appserver.core.entity;

public class ResponseEntity{

    private int httpStatus;

    private String contentType;

    private Object content;



    public ResponseEntity ok(){
        return this;
    }

    public ResponseEntity error(){
        return this;
    }


}
