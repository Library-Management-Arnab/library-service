package com.lms.ls.rest.config;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lms.svc.common.exception.ServiceException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ServiceExplorer {
	private static final Logger LOG = LoggerFactory.getLogger(ServiceExplorer.class);
	
	private DiscoveryClient discoveryClient;

	public String getUrl(String appId, String... components) {
		URI baseUri = getInstances(appId);
		if (components == null || components.length == 0) {
			return encode(baseUri.toString());
		}
		StringBuilder sb = new StringBuilder(baseUri.toString());

		for (int i = 0; i < components.length; i++) {
			sb.append("/");
			sb.append(components[i]);
		}
		String formedURL = sb.toString();
		LOG.info("Formed URL - [{}]", formedURL);
		return formedURL;
	}

	private URI getInstances(String appId) {
		List<ServiceInstance> instances = discoveryClient.getInstances(appId);
		if (CollectionUtils.isEmpty(instances)) {
			LOG.error(String.format("No service instance was found for app %s", appId));
			throw new ServiceException(String.format("Endpoint of app [%s] is not available.", appId));
		}
		List<URI> allUris = instances.stream().map(ServiceInstance::getUri).collect(Collectors.toList());
		LOG.info("{} instance(s) were found for app {} - [{}]", instances.size(), appId, allUris);
		return allUris.get(0);
	}

	String encode(String url) {
		try {
			return URLEncoder.encode(url.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("Error occurred", e);
			throw new ServiceException(e.getLocalizedMessage());
		}
	}
}
