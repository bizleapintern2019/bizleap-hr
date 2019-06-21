package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.EmployeeServiceJDBC;
import com.bizleap.hr.service.impl.EmployeeServiceJDBCImpl;

public class EmployeeServiceImplTest {
	
	EmployeeServiceJDBC employeeServiceJDBC = new EmployeeServiceJDBCImpl();
	
	@Test
	public void testSavingEmployee() {
		
		Employee employee = new Employee();
		employee.setBoId("EMP006");
		employee.setFirstName("Ron");
		employee.setLastName("James");
		employee.setAge(22);
		employee.setTitle("Developer");
		employee.setSalary(500000);
		employee.setEmail("ron@gmail.com");
		employee.setPhone("0946245215");
		
		Company workFor = new Company("COM002");
		employee.setWorkFor(workFor);
		employeeServiceJDBC.saveEmployee(employee);
	}
}