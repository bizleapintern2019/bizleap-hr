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
		Employee employee= new Employee();
		employee.setAge(23);
		employee.setBoId("EMP008");
		employee.setEmail("khin@gmail.com");
		employee.setFirstName("Khin");
		employee.setLastName("Chanmyae");
		employee.setPhone("01559122");
		employee.setSalary(400000);
		employee.setTitle("Staff");
		Company workFor= new Company();
		workFor.setBoId("C002");
		employee.setWorkFor(workFor);
		employeeService.saveEmployee(employee);
	}

}
