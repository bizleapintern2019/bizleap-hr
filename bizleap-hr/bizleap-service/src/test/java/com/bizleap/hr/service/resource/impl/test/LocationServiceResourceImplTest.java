package com.bizleap.hr.service.resource.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.LocationServiceRestClient;

public class LocationServiceResourceImplTest extends ServiceTest {

	LocationServiceRestClient locationServiceRestClient = new LocationServiceRestClient();
	
	@Test
	public void testGetAllLocations() throws ServiceUnavailableException {
		locationServiceRestClient.getAllLocations();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		locationServiceRestClient.findByLocationBoId("LOC001");
	}
	
	@Test
	public void testFindByName() throws ServiceUnavailableException {
		locationServiceRestClient.findByLocationName("Yangon");
	}
	
	@Test
	public void testSaveLocation() throws ServiceUnavailableException {
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");

		Department department = new Department();
		department.setBoId("DEPT001");
		department.setName("Test");
		department.setLocation(location);
		department.setParentDepartment(null);
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department);

		location.setDepartmentList(departmentList);

		locationServiceRestClient.saveLocation(location);
	}
}