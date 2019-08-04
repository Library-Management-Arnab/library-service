package com.lms.ls.rest.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.lms.svc.common.exception.InvalidCredentialsException;
import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.svc.common.model.User;

@Repository
public class UserServiceRepository {

	private static final String USER_SERVICE_BASE_URL = "http://localhost:8081/api/us";

	private RestTemplate restTemplate;

	public UserServiceRepository(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public AuthenticatedUser authenticate(User user) {
		ResponseEntity<AuthenticatedUser> response = restTemplate.postForEntity(USER_SERVICE_BASE_URL + "/login", user,
				AuthenticatedUser.class);
		HttpStatus statusCode = response.getStatusCode();

		if (statusCode.isError()) {
			throw new InvalidCredentialsException();
		}
		AuthenticatedUser body = response.getBody();
		
		return body;
	}
	public AuthenticatedUser authenticate(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		return authenticate(user);
	}

}
