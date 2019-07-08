package com.bizleap.hr.service.impl.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.LocationServiceRestClient;
import com.bizleap.service.LocationService;

public class LocationServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(LocationServiceImplTest.class);

	@Autowired
	LocationService locationService;

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

	@Test
	public void testGetAllLocations() throws ServiceUnavailableException {
		try {
			for (Location location : locationService.getAll()) {
				logger.info(location.getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
		assertEquals(2, locationService.getAll().size());
		
	}

	@Test
	public void testFindByBoId() {

		try {

			List<Location> locationList = locationService.findByBoId("LOC001");

			if (!CollectionUtils.isEmpty(locationList))
				logger.info("Location Name: " + locationList.get(0).getName());
		} catch (ServiceUnavailableException e) {
			logger.info(e);
		}

	}

	@Ignore
	@Test
	public void testFindByName() {
		try {
			List<Location> locationList = locationService.findByName("Yangon");
			if (!CollectionUtils.isEmpty(locationList))
				logger.info("Location BoId: " + locationList.get(0).getBoId() + "\nDepartment Name: "
						+ locationList.get(0).getDepartmentList().get(0).getName());
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
	}
}