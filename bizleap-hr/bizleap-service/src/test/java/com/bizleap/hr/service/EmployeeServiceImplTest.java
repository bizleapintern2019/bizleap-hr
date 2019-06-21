package com.bizleap.hr.service;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.EmployeeServiceJDBC;
import com.bizleap.hr.service.impl.EmployeeServiceJDBCImpl;

public class EmployeeServiceImplTest {
	EmployeeServiceJDBC employeeService = new EmployeeServiceJDBCImpl();
	
	@Ignore
	public void testSavingEmployee() {
		Employee employee = new Employee();
		employee.setBoId("EMP007");
		employee.setFirstName("Yamone");
		employee.setLastName("Zin");
		employee.setAge(20);
		employee.setEmail("yamonezin@gmail.com");
		employee.setTitle("Intern");
		employee.setSalary(40000);
		employee.setPhone("09766974698");
		Company workFor = new Company();
		workFor.setBoId("C004");
		employee.setWorkFor(workFor);
		employeeService.saveEmployee(employee);
	}
}
