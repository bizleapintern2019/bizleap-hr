package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;
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

	@Ignore
	@Test
	public void testSaveLocation() {

		Location location = new Location();
		location.setBoId("LOC003");
		location.setName("Hlaing");

		Department parentDepartment = new Department();
		parentDepartment.setBoId("DEPT006");
		parentDepartment.setName("Test");
		parentDepartment.setLocation(location);
		parentDepartment.setParentDepartment(null);

		Department department = new Department();
		department.setBoId("DEPT007");
		department.setName("Test1");
		department.setLocation(location);
		department.setParentDepartment(parentDepartment);

		Department department1 = new Department();
		department1.setBoId("DEPT008");
		department1.setName("Test2");
		department1.setLocation(location);
		department1.setParentDepartment(department);

		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department1);

		location.setDepartmentList(departmentList);

		try {
			logger.info("Location info: " + location.getBoId());
			locationService.saveLocation(location);
		} catch (IOException e) {
			logger.error(e);
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		logger.info(location);
	}
	
	private int assertLocation(Location location, String boId, String name) {
		if(location.getBoId().equals(boId)) {
			Assert.assertEquals(name, location.getName());
			return 1;
		}
		return 0;
	}
	
	private void testLocationList(List<Location> locationList) throws ServiceUnavailableException {
		Assert.assertTrue(locationList != null && locationList.size() == 2);
		int successCount = 0;
		for(Location location : locationList) {
			successCount += assertLocation(location, "LOC001", "Yangon");
			successCount += assertLocation(location, "LOC002", "Mandalay");
		}
		Assert.assertTrue(successCount == 2);
	}


	@Test
	public void testGetAllLocations() throws Exception {

		try {
			 testLocationList(locationService.getAll());
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
		Assert.assertEquals(2, locationService.getAll().size());
	}

	public int assertLocation(Location location,String boId,String name,String departmentList) {
		if(location.getBoId().equals(boId)) {
			Assert.assertEquals(location.getName(), name);
			Assert.assertEquals(location.getDepartmentList().get(0).getBoId(), departmentList);
			Assert.assertEquals(location.getDepartmentList().get(1).getBoId(), departmentList);
			return 1;
		}
		return 0;
	}
	
	@Test
	public void testFindByBoId() {
		try {
			List<Location> locationList = locationService.findByBoId("LOC001");

			if (!CollectionUtils.isEmpty(locationList)) {
				Assert.assertEquals("Yangon", locationList.get(0).getName());
			}
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}

	}

	@Test
	public void testFindByName() {
		try {
			List<Location> locationList = locationService.findByName("Yangon");
			if (!CollectionUtils.isEmpty(locationList)) {
				Assert.assertEquals("LOC001", locationList.get(0).getBoId());
				Assert.assertEquals(2, locationList.get(0).getDepartmentList().size());
			}
			
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
	}
}