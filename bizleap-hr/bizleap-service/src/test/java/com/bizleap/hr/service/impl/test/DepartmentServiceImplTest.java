package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Ignore;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.DepartmentService;
import com.bizleap.service.LocationService;



public class DepartmentServiceImplTest extends ServiceTest{
	
	private Logger logger = Logger.getLogger(DepartmentServiceImplTest.class);

	@Autowired
	DepartmentService departmentService;
	
	@Autowired 
	LocationService locationService;
	
	@Ignore
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
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		logger.info(department);
	}
	
	@Test
	public void testFindByBoId() {
		
		try {
			
			List<Department> departmentList = departmentService.findByBoId("DEPT001");
			if(!CollectionUtils.isEmpty(departmentList))
				logger.info("Department Name: " + departmentList.get(0).getName() + departmentList.get(0).getJobList().size());
		} catch (ServiceUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

}