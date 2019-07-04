package com.bizleap.internhr.service.resource.impl.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class LocationServiceRestClient {

		private static final Logger logger = Logger.getLogger(LocationServiceRestClient.class);
		//private final static String SERVICEURL = "http://167.99.57.200:8081/bizleap-clb-application";
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
				e.printStackTrace();
				logger.error("ERRROR - " + e.getMessage() + ", " + response);
			}
		}
}
