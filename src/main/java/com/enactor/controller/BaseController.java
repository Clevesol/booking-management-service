package com.enactor.controller;

import com.enactor.util.JSONMapper;
import jakarta.servlet.http.HttpServletResponse;

public class BaseController {

    protected final JSONMapper jsonMapper = JSONMapper.getInstance();

    protected void initAndWriteResponse(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json;");
        jsonMapper.writeToStream(response.getOutputStream(), obj);
    }
}
