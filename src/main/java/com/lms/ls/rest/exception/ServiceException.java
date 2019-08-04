package com.lms.ls.rest.exception;

import org.springframework.http.HttpStatus;

import com.lms.svc.common.exception.ApplicationError;
import com.lms.svc.common.model.ErrorObject;

public class ServiceException extends ApplicationError {
	private static final long serialVersionUID = -8066458944260533648L;

	private final String errorTime;
	private final HttpStatus httpStatus;
	private final String message;
	private final int errorCode;

	public ServiceException(ErrorObject errorObject) {
		this.message = String.format(errorObject.getMessage());
		this.httpStatus = errorObject.getHttpStatus();
		this.errorCode = errorObject.getErrorCode();
		this.errorTime = errorObject.getErrorTime();
	}
	
	@Override
	public String getErrorTime() {
		return errorTime;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}
}
