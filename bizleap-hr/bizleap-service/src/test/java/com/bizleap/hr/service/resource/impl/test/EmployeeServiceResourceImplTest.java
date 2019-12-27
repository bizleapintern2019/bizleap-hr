package com.bizleap.hr.service.resource.impl.test;

import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.EmployeeServiceRestClient;

public class EmployeeServiceResourceImplTest extends ServiceTest {

	EmployeeServiceRestClient employeeServiceRestClient = new EmployeeServiceRestClient();
	
	@Test
	public void testGetAllEmployee() throws ServiceUnavailableException {
		employeeServiceRestClient.getAllEmployee();
	}
	
	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		employeeServiceRestClient.findByEmployeeBoId("EMP001");
	}
	
	@Test
	public void testFindByEmployeeFirstName() throws ServiceUnavailableException {
		employeeServiceRestClient.findByEmployeeFirstName("Soe");
	}
	
	@Test
	public void testFindByEmployeeLastName() throws ServiceUnavailableException {
		employeeServiceRestClient.findByEmployeeLastName("Min Thein");
	}
	
	@Test
	public void testFindByEmployeeGender() throws ServiceUnavailableException {
		employeeServiceRestClient.findByEmployeeGender("Male");
	}
}