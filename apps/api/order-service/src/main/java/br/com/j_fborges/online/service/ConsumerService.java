package br.com.j_fborges.online.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	
	@Value("${application.consumerService.endpointFetchConsumer}")
	private String urlEndpointFetchConsumer;
	
	private RestUtils restUtils;
	
	@Autowired
	public ConsumerService(RestUtils restUtils) {
		this.restUtils = restUtils;
	}

	public Boolean isConsumerRegistered(String consumerId) {
		RestRequest restRequest = new RestRequest(HttpMethod.GET, null);
		restRequest.setContentType(MediaType.APPLICATION_JSON);
		restRequest.setAcceptable(Collections.singletonList(MediaType.APPLICATION_JSON));
		String urlComParam = urlEndpointFetchConsumer.replace("{id}", consumerId);
		ResponseEntity<Boolean> response = restUtils.execute(urlComParam, restRequest, Boolean.class);
		return response.getBody();
	}
}
