package com.bizleap.hr.service.impl.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.DepartmentService;

public class DepartmentServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(DepartmentServiceImplTest.class);

	@Autowired
	private DepartmentService departmentService;

	@Test
	public void testSaveDepartment() {

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

		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department);

		location.setDepartmentList(departmentList);

		try {
			departmentService.saveDepartment(department);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		logger.info(department);
	}
	
	public static int testAssertDepartment(Department department,String boId,String name) {
		if(department.getBoId().equals(boId)) {
			Assert.assertEquals(name, department.getName());
			return 1;
		}
		return 0;
	}

	public static void testDepartment(List<Department> departmentList) {
		assertTrue(departmentList!=null);
		int successsCount=0;
		for(Department department:departmentList) {
			successsCount+=testAssertDepartment(department, "DEPT001", "BOD");
			successsCount+=testAssertDepartment(department, "DEPT002", "Engineering");
			successsCount+=testAssertDepartment(department, "DEPT003", "Internship");
			successsCount+=testAssertDepartment(department, "DEPT004", "Customer Support");
		}
		assertTrue(successsCount==4);
	}

	@Test
	public void testGetAllDepartment() throws ServiceUnavailableException {
		try {
			testDepartment(departmentService.getAll());
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}

	@Test
	public void testFindByBoId() {
		try {
			List<Department> departmentList = departmentService.findByBoId("DEPT003");
			if (!CollectionUtils.isEmpty(departmentList)) {
				Assert.assertEquals(6, departmentList.get(0).getJobList().size());
				Assert.assertEquals("InternShip", departmentList.get(0).getName());
			}
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}

	@Test
	public void testFindByName() {
		try {
			List<Department> departmentList = departmentService.findByName("Engineering");
			if (!CollectionUtils.isEmpty(departmentList)) 
				Assert.assertEquals("DEPT002", departmentList.get(0).getBoId());
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}
}
