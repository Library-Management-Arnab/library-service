package com.lms.ls.rest.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.lms.ls.rest.model.client.UserLoginRequest;
import com.lms.ls.rest.repository.UserLoginRepository;
import com.lms.svc.common.model.AuthenticatedUser;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class LibraryServiceSecurityConfig extends WebSecurityConfigurerAdapter  {
	private LMSAuthenticationProvider lmsAuthenticationProvider;

	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		ProviderManager providerManager = new ProviderManager(Arrays.asList(lmsAuthenticationProvider));
		return providerManager;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authenticationProvider(lmsAuthenticationProvider);
	}
	@Component
	@AllArgsConstructor
	private static class LMSAuthenticationProvider implements AuthenticationProvider {
		private UserLoginRepository loginRepository;
		
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			UserLoginRequest loginRequest = new UserLoginRequest(authentication.getName(), authentication.getCredentials().toString());
			
			AuthenticatedUser user = loginRepository.login(loginRequest);
			
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(user.getUserRight());
			
			return user.isActive() ? new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities) : null;
		}


		@Override
		public boolean supports(Class<?> authentication) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
