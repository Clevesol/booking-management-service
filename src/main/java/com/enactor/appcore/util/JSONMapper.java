package com.enactor.appcore.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;

public class JSONMapper extends ObjectMapper {

    private static JSONMapper jsonMapper;
    private static boolean initialized = false;

    public JSONMapper(){
        this.init();
    }



    private void init(){
        if(!initialized) {
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            initialized = true;
        }
    }

    public Class<?> toObject(InputStream inputStream, Class<?> type) throws Exception{
        return this.readValue(inputStream, type.getClass());
    }

    public void writeToStream(OutputStream outputStream, Object obj) throws Exception{
        this.writeValue(outputStream, obj);
    }

    public static JSONMapper getInstance(){
        if(null == jsonMapper) jsonMapper = new JSONMapper();
        return jsonMapper;
    }
}
