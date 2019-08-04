package com.lms.ls.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LibraryServiceConfig {
	@Bean
	public WebClient webClient() {
		WebClient.Builder builder = WebClient.builder();
		return WebClient.create();
	}
}
