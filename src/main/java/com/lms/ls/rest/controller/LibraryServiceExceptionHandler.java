package com.lms.ls.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.ApplicationError;
import com.lms.svc.common.model.ErrorObject;

@RestControllerAdvice
public class LibraryServiceExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceExceptionHandler.class);
	
	@ExceptionHandler(value = { 
			ApplicationError.class
		})
	public ResponseEntity<Object> handleApplicationException(ApplicationError e) {
		LOG.error("Exception occurred - ", e);
		ErrorObject eo = new ErrorObject();
		eo.setErrorCode(e.getErrorCode());
		eo.setMessage(String.format("Error occurred with message [%s] and type [%s]", e.getMessage(), e.getClass().getSimpleName()));
		eo.setErrorTime(e.getErrorTime());

		return new ResponseEntity<>(eo, e.getHttpStatus());
	}
	@ExceptionHandler(value = { 
			Exception.class
		})
	public ResponseEntity<Object> handleGenericException(Exception e) {
		LOG.error("Generic Exception occurred - ", e);
		ErrorObject eo = new ErrorObject();
		eo.setErrorCode(1010);
		eo.setMessage(String.format("Error occurred with message [%s] and type [%s]", e.getMessage(), e.getClass().getSimpleName()));
		eo.setErrorTime(ApplicationCommonConstants.getCurrentDateAsString());

		return new ResponseEntity<>(eo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
