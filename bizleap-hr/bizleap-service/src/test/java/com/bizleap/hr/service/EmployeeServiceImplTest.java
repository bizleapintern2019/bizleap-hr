package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.EmployeeBuilder;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	EmployeeService employeeService=new EmployeeServiceImpl();
	@Test
	public void testSavingEmployee(){
		Employee employee=new Employee("EMP002");
		employee.setFirstName("ThuYa");
		employee.setLastName("Oo");
		employee.setAge(21);
		employee.setSalary(44344);
		employee.setEmail("446fed@gmail.com");
		employee.setTitle("Hello");
		employee.setPhone("03232");
		Company workfor=new Company("COM001");
		workfor.setName("BizLeapTechnology");
		employee.setWorkFor(workfor);
		employeeService.saveEmployee(employee);
	}
}
