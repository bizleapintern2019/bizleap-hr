package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.DepartmentService;

public class DepartmentServiceImplTest extends ServiceTest{
	
	private Logger logger = Logger.getLogger(DepartmentServiceImplTest.class);

	@Autowired
	DepartmentService departmentService;

	@Test
	public void departmentServiceTest() {
		
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");
		
		Department parentDepartment = new Department();
		parentDepartment.setBoId("DEPT002");
		parentDepartment.setName("BOD");
		parentDepartment.setLocation(location);
		parentDepartment.setParentDepartment(new Department(""));
		
		
		Department department = new Department();
		department.setBoId("DEPT001");
		department.setName("BOD");
		department.setLocation(location);
		department.setParentDepartment(parentDepartment);
		
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
