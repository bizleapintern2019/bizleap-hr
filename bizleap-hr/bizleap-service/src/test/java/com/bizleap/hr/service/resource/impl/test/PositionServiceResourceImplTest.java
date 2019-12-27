package com.bizleap.hr.service.resource.impl.test;

import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.PositionServiceRestClient;

public class PositionServiceResourceImplTest extends ServiceTest {

	private PositionServiceRestClient positionServiceRestClient = new PositionServiceRestClient();
	
	@Test
	public void testGetAllPosition() throws ServiceUnavailableException {
		positionServiceRestClient.getAllPositions();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		positionServiceRestClient.findByPositionBoId("JOB001-1");
	}
}