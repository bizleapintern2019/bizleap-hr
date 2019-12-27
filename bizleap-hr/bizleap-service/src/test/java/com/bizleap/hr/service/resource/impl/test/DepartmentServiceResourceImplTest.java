package com.bizleap.hr.service.resource.impl.test;

import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.DepartmentServiceRestClient;

public class DepartmentServiceResourceImplTest extends ServiceTest {

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
}