package com.bizleap.hr.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.impl.EmployeeServiceJDBCImpl;

public class EmployeeServiceImplTest{
	@Autowired
	EmployeeServiceJDBC employeeService;
	@Test
	public void testSavingEmployee() {
		Employee employee=new Employee("EMP001");
		employee.setAge(22);
		employee.setFirstName("Saung Hnin");
		employee.setLastName("Phyu");
		employee.setEmail("gg@gmail.com");
		employee.setPhone("0996655099999");
		employee.setSalary(100000);
		employee.setTitle("Developer");
		Company workFor=new Company("Biz001");
		workFor.setCompanyName("BizLeap Technology");
		employee.setWorkFor(workFor);
		employeeService.saveEmployee(employee);
	}
}
