package com.bizleap.internhr.service.resource.impl.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bizleap.commons.domain.entity.Location;

public class LocationServiceRestClient {

		private static final Logger logger = Logger.getLogger(LocationServiceRestClient.class);
		private final static String SERVICEURL = "http://localhost:8080/bizleap-internhr-application";

		public void getAllLocations() {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(headers);
			logger.info("Request is: " + entity);

			String url = SERVICEURL + "/locations/list";
			logger.info("Service url is: " + url);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			logger.info("Calling webservice..." + builder);

			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<List> response = null;

			try {
				response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
				logger.info("Location List: " + response.getBody());

			} catch (Exception e) {
				logger.error("ERRROR - " + e.getMessage() + ", " + response);
			}
		}
		
		public void findByLocationBoId(String boId) {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			logger.info("Request is: " + entity);
			String url = SERVICEURL + "/locations/find/"+boId;
			logger.info("service url is: " + url);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			RestTemplate restTemplate = new RestTemplate();
			logger.info("calling webservice..." + builder);

			HttpEntity<List> response = null;

			try {
				response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
				logger.info("Find Location: " + response.getBody());
			} catch (Exception e) {
				logger.error("ERRROR - " + e.getMessage() + ", " + response);
			}
		}
		
		public void findByLocationName(String name) {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			logger.info("Request is: " + entity);
			String url = SERVICEURL + "/locations/find/"+name;
			logger.info("service url is: " + url);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			RestTemplate restTemplate = new RestTemplate();
			logger.info("calling webservice..." + builder);

			HttpEntity<List> response = null;

			try {
				response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
				logger.info("Find Location: " + response.getBody());
			} catch (Exception e) {
				logger.error("ERRROR - " + e.getMessage() + ", " + response);
			}
		}
		
		public void saveLocation(Location location) {

			// Prepare the header
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<Location> entityHeader = new HttpEntity<Location>(location, headers);
			logger.info("Request is: " + entityHeader);

			// Prepare the URL
			String url = SERVICEURL + "/locations/new";
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
				logger.info("after service");

			} catch (Exception e) {
				logger.error("ERRROR is - " + e.getMessage() + ", " + response);
			}
		}
}