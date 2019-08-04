package com.lms.ls.rest.model.client;

import lombok.Data;

@Data
public class LibraryRestResponse<T> {
	private T body;
	private RequestMetaData metaData;
	private String finalStatus;
}
