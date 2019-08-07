package com.lms.ls.rest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.svc.common.exception.ServiceException;
import com.lms.svc.common.model.ErrorObject;

@Component
public abstract class BaseServiceRepository {
    @Autowired
    private ObjectMapper objectMapper;

    protected final <T> T checkForErrorAndReturn(ResponseEntity<String> rawResponse, Class<T> type) {
        String body = rawResponse.getBody();
        try {
            if (rawResponse.getStatusCode().isError()) {
                ErrorObject error = objectMapper.readValue(body, ErrorObject.class);;
                throw new ServiceException(error);
            } else {
                return objectMapper.readValue(body, type);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
