package com.lms.ls.rest.config;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lms.svc.common.constants.ApplicationCommonConstants;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DiscoveryClientConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DiscoveryClientConfig.class);
	private DiscoveryClient discoveryClient;
	
	public URI getBookServiceUrl() {
		return getInstances(ApplicationCommonConstants.BOOK_SERVICE_APP_NAME);
	}
	public URI getUserServiceUrl() {
		return getInstances(ApplicationCommonConstants.USER_SERVICE_APP_NAME);
	}
	private URI getInstances(String appId) {
		List<ServiceInstance> instances = discoveryClient.getInstances(appId);
		if(CollectionUtils.isEmpty(instances)) {
			LOG.error(String.format("No service instance was found for app %s", appId));
			return null;
		}
		List<URI> allUris = instances.stream().map(ServiceInstance::getUri).collect(Collectors.toList());
		LOG.info("{} instance(s) were found for app {} - [{}]", instances.size(), appId, allUris);
		return allUris.get(0);
	}
}
