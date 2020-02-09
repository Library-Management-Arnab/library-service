package com.lms.ls.rest.model.client;

import lombok.Data;

@Data
public class UserLoginRequest {
	public UserLoginRequest() {
	}

	public UserLoginRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	private String userName;
	private String password;
}
