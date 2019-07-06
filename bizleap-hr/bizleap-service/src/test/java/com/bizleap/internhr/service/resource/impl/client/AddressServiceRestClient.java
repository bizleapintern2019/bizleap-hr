package com.bizleap.internhr.service.resource.impl.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Location;

public class AddressServiceRestClient {
	
	private static final Logger logger = Logger.getLogger(AddressServiceRestClient.class);
	//private final static String SERVICEURL = "http://167.99.57.200:8081/bizleap-clb-application";
	private final static String SERVICEURL = "http://localhost:8080/bizleap-internhr-application";

	public void getAllAddress() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		logger.info("Request is: " + entity);

		String url = SERVICEURL + "/address/list";
		logger.info("Service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		logger.info("Calling webservice..." + builder);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Address List: " + response.getBody());

		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressBoId(String boId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+boId;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressCity(String city) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+city;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressContactAddress(String contactAddress) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+contactAddress;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressPermanentAddress(String permanentAddress) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+permanentAddress;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressState(String state) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+state;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressCountry(String country) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+country;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void findByAddressLocationBoId(String locationBoId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		logger.info("Request is: " + entity);
		String url = SERVICEURL + "/address/find/"+locationBoId;
		logger.info("service url is: " + url);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("calling webservice..." + builder);

		HttpEntity<List> response = null;

		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, List.class);
			logger.info("Find Address: " + response.getBody());
		} catch (Exception e) {
			logger.error("ERRROR - " + e.getMessage() + ", " + response);
		}
	}
	
	public void saveAddress(Address address) {

		// Prepare the header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Address> entityHeader = new HttpEntity<Address>(address, headers);
		logger.info("Request is: " + entityHeader);

		// Prepare the URL
		String url = SERVICEURL + "/address/new";
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
