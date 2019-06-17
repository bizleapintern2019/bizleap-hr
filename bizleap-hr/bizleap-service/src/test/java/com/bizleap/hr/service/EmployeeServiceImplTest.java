package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	
	EmployeeService employeeService = new EmployeeServiceImpl();
	
	@Test
	public void testSavingEmployee() {
		
		Employee employee = new Employee();
		employee.setBoId("EMP001");
		employee.setFirstName("Roger");
		employee.setLastName("Bob");
		employee.setAge(22);
		employee.setTitle("Developer");
		employee.setSalary(500000);
		employee.setEmail("roger@gmail.com");
		employee.setPhone("0946245215");
		
		Company workFor = new Company("COM001");
		employee.setWorkFor(workFor);
		employeeService.saveEmployee(employee);
	}
}