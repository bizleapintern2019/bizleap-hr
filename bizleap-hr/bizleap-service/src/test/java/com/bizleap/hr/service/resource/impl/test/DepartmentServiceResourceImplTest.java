package com.bizleap.hr.service.resource.impl.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.impl.test.LocationServiceImplTest;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.DepartmentServiceRestClient;

public class DepartmentServiceResourceImplTest extends ServiceTest {
	
	private Logger logger = Logger.getLogger(DepartmentServiceResourceImplTest.class);

	DepartmentServiceRestClient departmentServiceRestClient = new DepartmentServiceRestClient();
	
	@Test
	public void testGetAllDepartments() throws ServiceUnavailableException {
		departmentServiceRestClient.getAllDepartment();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		departmentServiceRestClient.findByDepartmentBoId("DEPT001");
	}
	
	@Test
	public void testFindByName() throws ServiceUnavailableException {
		departmentServiceRestClient.findByDepartmentName("Engineering");
	}
	
	@Test
	public void testFindByParentDepartment() throws ServiceUnavailableException {
		departmentServiceRestClient.findByParentDepartment("DEPT001");
	}

}


