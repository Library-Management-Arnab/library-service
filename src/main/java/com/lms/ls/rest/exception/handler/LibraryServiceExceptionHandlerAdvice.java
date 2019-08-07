package com.lms.ls.rest.exception.handler;

import com.lms.ls.rest.util.LibraryServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class LibraryServiceExceptionHandlerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceExceptionHandlerAdvice.class);
    @ExceptionHandler(value = {
            HttpClientErrorException.class
    })
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {
        LOG.error(String.format("HttpClientErrorException occurred - %s", e.getLocalizedMessage()));
        String responseBody = e.getResponseBodyAsString();
        return ResponseEntity.status(e.getStatusCode())
                .headers(defaultHeaders())
                .body(LibraryServiceUtil.returnResponse(responseBody, e.getStatusCode()));
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
        headers.add(HttpHeaders.CACHE_CONTROL, "No-Cache");
        return headers;
    }

    @ExceptionHandler(value = {
            WebClientResponseException.class
    })
    public Mono<Object> handleWebClientErrorException(WebClientResponseException e) {
        LOG.error(String.format("WebClientResponseException occurred - %s", e.getLocalizedMessage()));
        String responseBody = e.getResponseBodyAsString();
        return Mono.just(responseBody);
    }
}
