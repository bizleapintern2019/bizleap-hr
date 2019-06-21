package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.EmployeeService;
import com.bizleap.hr.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	
	EmployeeService employeeService = new EmployeeServiceImpl();
	@Test
	public void testSavingEmployee(){
		Employee employee = new Employee("EMP001");
		employee.setAge(21);
		employee.setFirstName("Saw Sandi");
		employee.setLastName("Tin");
		employee.setEmail("saw@gmail.com");
		employee.setPhone("0977");
		employee.setSalary(40000);
		employee.setTitle("Software Development");
		
		Company workfor = new Company ("COM001");
		workfor.setName("Bizleap Technology");
		employee.setWorkFor(workfor);
		employeeService.saveEmployee(employee);
	}
}
