package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

	EmployeeService employeeService=new EmployeeServiceImpl();
	@Test
	public void testSavingEmployee() {
		Employee employee=new Employee("EMP001");
		employee.setAge(22);
		employee.setFirstname("Bob");
		employee.setLastname("Gilman");
		employee.setEmail("bob@gmail.com");
		employee.setPhone("093022");
		employee.setSalary(30233);
		employee.setTitle("developer");
		Company workfor=new Company("COM001");
		workfor.setCompanyName("Bizleap Technology");
		employee.setWorkFor(workfor);
		employeeService.saveEmployee(employee);
		
	}
}
