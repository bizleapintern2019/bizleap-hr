package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.DepartmentService;

public class DepartmentServiceTest {
	
	private Logger logger = Logger.getLogger(DepartmentServiceTest.class);

	@Autowired
	DepartmentService departmentService;

	@Test
	public void departmentServiceTest() {
		
		Department department = new Department();
		department.setBoId("DEPT001");
		department.setName("BOD");
		department.setLocation(new Location("LOC001"));
		department.setParentDepartment(new Department("null"));
		
		try {
			departmentService.saveDepartment(department);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(department);
	}
		

}
