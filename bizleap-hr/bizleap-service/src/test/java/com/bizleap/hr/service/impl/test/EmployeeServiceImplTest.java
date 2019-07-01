package com.bizleap.hr.service.impl.test;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.EmployeeService;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImplTest {

	private Logger logger = Logger.getLogger(EmployeeServiceImplTest.class);

	@Autowired
	EmployeeService employeeService;

	@Test
	public void employeeServiceTest() {
		logger.info("****************employee");
		Employee employee = new Employee();
		employee.setBoId("EMP001");
		employee.setTitle("Mg");
		employee.setFirstName("Aung");
		employee.setLastName("Aung");
		employee.setPosition(new Position("JOB001-1"));
		employee.setEntranceDate("30-06-2019");
		employee.setDateOfBirth("06-09-1995");
		employee.setGender("Male");
		employee.setEmail("aung@gmail.com");
		employee.setPhone("0925645652");
		employee.setAddress(new Address("ADR001"));
		
		try {
			employeeService.saveEmployee(employee);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(employee);
	}

}
