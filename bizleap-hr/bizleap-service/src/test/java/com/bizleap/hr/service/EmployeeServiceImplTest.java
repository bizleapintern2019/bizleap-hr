package com.bizleap.hr.service;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

	EmployeeService employeeService = new EmployeeServiceImpl();
	
	@Ignore
	public void testSavingEmployee() {
		Employee employee = new Employee();
		employee.setBoId("EMP004");
		employee.setFirstName("John");
		employee.setLastName("Ace");
		employee.setAge(23);
		employee.setEmail("johnace@gmail.com");
		employee.setTitle("Android Developer");
		employee.setSalary(800000);
		employee.setPhone("09795324758");
		Company workFor = new Company();
		workFor.setBoId("C001");
		employee.setWorkFor(workFor);
		employeeService.saveEmployee(employee);
	}
}