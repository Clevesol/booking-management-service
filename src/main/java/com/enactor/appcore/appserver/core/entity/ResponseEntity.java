package com.enactor.appcore.appserver.core.entity;

public class ResponseEntity{

    private int status;

    private String contentType;

    private Object body;

    public ResponseEntity(){

    }

    public ResponseEntity(Object body){
        this.body = body;
    }

    public ResponseEntity ok(){
        this.setStatus(HttpStatusCode.OK.getCode());
        return this;
    }

    public ResponseEntity notFound(){
        this.setStatus(HttpStatusCode.NOT_FOUND.getCode());
        this.setBody(HttpStatusCode.NOT_FOUND);
        return this;
    }

    public ResponseEntity error(int status){
        this.setStatus(status);
        return this;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
