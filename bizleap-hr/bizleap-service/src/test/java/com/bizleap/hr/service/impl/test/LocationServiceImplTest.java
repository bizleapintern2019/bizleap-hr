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
import com.bizleap.internhr.service.resource.LocationServiceResource;
import com.bizleap.internhr.service.resource.impl.client.LocationServiceRestClient;
import com.bizleap.service.LocationService;

public class LocationServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(LocationServiceImplTest.class);

	@Autowired
	LocationService locationService;
	
	private LocationServiceRestClient locationServiceRestClient = new LocationServiceRestClient();
	
	@Test
	public void testSaveLocation() {
		
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");
		
		Department parentDepartment = new Department();
		parentDepartment.setBoId("DEPT001");
		parentDepartment.setName("BOD");
		parentDepartment.setLocation(location);
		parentDepartment.setParentDepartment(null);			
		
		Department department = new Department();
		department.setBoId("DEPT002");
		department.setName("BOD");
		department.setLocation(location);
		department.setParentDepartment(parentDepartment);
		
		Department department1 = new Department();
		department1.setBoId("DEPT003");
		department1.setName("BOD");
		department1.setLocation(location);
		department1.setParentDepartment(department);
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department1);
		
		location.setDepartmentList(departmentList);
		
		
		logger.info("Location info: " + location.toString());
		locationServiceRestClient.saveLocation(location);

		logger.info(location);
	}
}

	/*@Test
	public void testSaveLocation() {
		
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");
		
		Department parentDepartment = new Department();
		parentDepartment.setBoId("DEPT001");
		parentDepartment.setName("BOD");
		parentDepartment.setLocation(location);
		parentDepartment.setParentDepartment(null);			
		
		Department department = new Department();
		department.setBoId("DEPT002");
		department.setName("BOD");
		department.setLocation(location);
		department.setParentDepartment(parentDepartment);
		
		Department department1 = new Department();
		department1.setBoId("DEPT003");
		department1.setName("BOD");
		department1.setLocation(location);
		department1.setParentDepartment(department);
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department1);
		
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
	}*/
