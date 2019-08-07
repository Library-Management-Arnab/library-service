package com.lms.ls.rest.exception.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.svc.common.model.ErrorObject;
import com.lms.svc.common.util.LMSResponseUtil;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class LibraryServiceExceptionHandlerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceExceptionHandlerAdvice.class);
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @ExceptionHandler(value = {
            HttpClientErrorException.class
    })
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {
        LOG.error(String.format("HttpClientErrorException occurred - %s", e.getLocalizedMessage()));
        String responseBody = e.getResponseBodyAsString();
        return LMSResponseUtil.returnResponse(getErrorObject(responseBody), e.getStatusCode());
    }

    @ExceptionHandler(value = {
            WebClientResponseException.class
    })
    public Mono<Object> handleWebClientErrorException(WebClientResponseException e) {
        LOG.error(String.format("WebClientResponseException occurred - %s", e.getLocalizedMessage()));
        String responseBody = e.getResponseBodyAsString();
        return Mono.just(responseBody);
    }
    
    private ErrorObject getErrorObject(String jsonString) {
    	try {
                ErrorObject error = objectMapper.readValue(jsonString, ErrorObject.class);
                return error;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
