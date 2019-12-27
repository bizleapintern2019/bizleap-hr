package com.bizleap.hr.service.resource.impl.test;

import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.AddressServiceRestClient;

public class AddressServiceResourceImplTest extends ServiceTest {

	AddressServiceRestClient addressServiceRestClient = new AddressServiceRestClient();
	
	@Test
	public void testGetAllAddress() throws ServiceUnavailableException {
		addressServiceRestClient.getAllAddress();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		addressServiceRestClient.findByAddressBoId("ADR001");
	}
	
	@Test
	public void testFindByCity() throws ServiceUnavailableException {
		addressServiceRestClient.findByAddressCity("Yangon");
	}
	
	
	@Test
	public void testFindByState() throws ServiceUnavailableException {
		addressServiceRestClient.findByAddressState("Yangon");
	}
	
	@Test
	public void testFindByCountry() throws ServiceUnavailableException {
		addressServiceRestClient.findByAddressCountry("Myanmar");
	}
	
	@Test
	public void testFindByLocationBoId() throws ServiceUnavailableException {
		addressServiceRestClient.findByAddressLocationBoId("LOC001");
	}
}
