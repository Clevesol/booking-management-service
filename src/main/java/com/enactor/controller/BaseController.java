package com.enactor.controller;

import com.enactor.appcore.util.JSONMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

public class BaseController {

    protected final JSONMapper jsonMapper = JSONMapper.getInstance();

    protected <T> T wrapToDTO(InputStream inputStream, Class<T> targetClass) throws Exception {

        // 1. Basic Validation: Check if inputs are null
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null");
        }
        if (targetClass == null) {
            throw new IllegalArgumentException("Target class type cannot be null");
        }

        try  {

            if (inputStream.available() == 0) {
                throw new IOException("The input stream is empty");
            }

            T result = (T) jsonMapper.toObject(inputStream, targetClass.getClass());


            if (result == null) {
                throw new RuntimeException("Mapping failed: Resulting object is null");
            }

            return result;

        } catch (IOException e) {
            // Handle stream reading errors
            throw new RuntimeException("Failed to read from input stream", e);
        } catch (Exception e) {
            // Handle mapping errors (like JSON syntax errors)
            throw new Exception("Error during object mapping: " + e.getMessage(), e);
        }
    }
}
