package com.bizleap.hr.service.resource.impl.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.impl.test.LocationServiceImplTest;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.LocationServiceRestClient;

public class DepartmentServiceResourceImplTest extends ServiceTest {
	
	private Logger logger = Logger.getLogger(DepartmentServiceResourceImplTest.class);

	LocationServiceRestClient locationServiceRestClient = new LocationServiceRestClient();
	
	@Test
	public void testGetAllLocations() throws ServiceUnavailableException {
		locationServiceRestClient.getAllLocations();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		locationServiceRestClient.findByLocationBoId("LOC001");
	}

}
