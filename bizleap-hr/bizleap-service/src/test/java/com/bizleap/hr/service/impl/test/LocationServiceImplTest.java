package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.LocationService;

public class LocationServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(LocationServiceImplTest.class);

	@Autowired
	LocationService locationService;

	@Test
	public void testSaveLocation() {
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(new Department("JOB001-2"));
		

		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");
		location.setDepartmentList(departmentList);
		
		try {
			logger.info("Location info: " + location.toString());
			locationService.saveLocation(location);
		} 
		catch (IOException e) {
			logger.error(e);
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		logger.info(location);
	}
}