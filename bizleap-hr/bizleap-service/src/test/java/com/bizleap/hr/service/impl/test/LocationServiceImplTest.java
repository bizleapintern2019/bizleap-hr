package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.LocationService;

public class LocationServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(LocationServiceImplTest.class);

	@Autowired
	LocationService locationService;

	@Test
	public void locationServiceTest() {
		logger.info("****************location");
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");
		logger.info("****************location");
		try {
			logger.info("Location info: " + location.toString());
			locationService.saveLocation(location);
			logger.info("****************location");
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}

		logger.info(location);
	}
}