package com.lms.ls.rest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.ls.rest.exception.ServiceException;
import com.lms.svc.common.model.ErrorObject;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Component
public abstract class BaseServiceRepository {
	private ObjectMapper objectMapper;

	@SuppressWarnings("unchecked")
	protected final <T> T checkForError(Class<T> type, ResponseEntity<Object> responseEntity) {
		Object body = responseEntity.getBody();
		if(responseEntity.getStatusCode().isError()) {
			throw new ServiceException((ErrorObject) body);
		}

		return (T)body;
	}
}
