package com.lms.ls.rest.repository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.lms.ls.rest.config.ServiceExplorer;
import com.lms.ls.rest.model.client.UserLoginRequest;
import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.model.AuthenticatedUser;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserLoginRepository extends BaseServiceRepository {
	
	private ServiceExplorer serviceExplorer;
	private RestTemplate restTemplate;
	
	public AuthenticatedUser login(UserLoginRequest loginRequest) {
		String loginURL = serviceExplorer.getUrl(ApplicationCommonConstants.USER_SERVICE_APP_ID, ApplicationCommonConstants.USER_SERVICE_BASE_URI, "login");
		
		HttpEntity<UserLoginRequest> loginRequestEntity = new HttpEntity<UserLoginRequest>(loginRequest);
		
		ResponseEntity<String> rawLoginResponse = restTemplate.exchange(loginURL, HttpMethod.POST, loginRequestEntity, String.class);
		
		return checkForErrorAndReturn(rawLoginResponse, AuthenticatedUser.class);
	}
}
