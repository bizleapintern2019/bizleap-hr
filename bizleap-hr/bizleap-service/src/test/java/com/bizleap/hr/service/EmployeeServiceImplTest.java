package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.impl.EmployeeServiceJDBCImpl;

public class EmployeeServiceImplTest {

	EmployeeServiceJDBC employeeService=new EmployeeServiceJDBCImpl();
//	@Test
	public void testSavingEmployee() {
		Employee employee = new Employee("EMP001");
		employee.setAge(12);
		employee.setEmail("@");
		employee.setFirstName("La");
		employee.setLastName("La");
		employee.setSalary(1000);
		employee.setPhone("Asd");
		employee.setTitle("asd");
		Company workFor= new Company("COM002");
		workFor.setName("BizLeap");
		employee.setWorkFor(workFor);
		
	//	employeeService.saveEmployee(employee);
	}
}
