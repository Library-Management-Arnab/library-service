package com.lms.ls.rest.model.client;

import java.util.Date;

import lombok.Data;

@Data
public class RequestMetaData {
	private String requestedBy;
	private Date RequestTimestamp;
	private String userRight;
}
