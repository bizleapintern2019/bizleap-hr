package com.bizleap.internhr.service.resource.impl.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;

public class PositionServiceRestClient {
	private static final Logger logger = Logger.getLogger(JobServiceRestClient.class);
	private final static String SERVICEURL = "http://localhost:8080/bizleap-internhr-application";
	
	public void savePosition(Position position) {

		// Prepare the header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Position> entityHeader = new HttpEntity<Position>(position, headers);
		logger.info("Request is: " + entityHeader);

		// Prepare the UR
		String url = SERVICEURL + "/positions/new";
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		logger.info("calling webservice..." + builder);

		// RESTTemplate to call the service
		RestTemplate restTemplate = new RestTemplate();

		// Data type for response
		HttpEntity<String> response = null;
		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entityHeader,
					String.class);
			logger.info("after service" + response.getBody().toString());

		} catch (Exception e) {
			logger.error("ERRROR is - " + e.getMessage() + ", " + response);
		}

	}

	public void getAllPositions() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		logger.info("Request is: " + entity);

		String url = SERVICEURL + "/positions/list";
		logger.info("Service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		logger.info("Calling webservice..." + builder);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Job List: " + response.getBody());

		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByPositionBoId(String boId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("boId", boId);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/jobs/find";
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, List.class);
			logger.info("Find Jobs: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}

}
