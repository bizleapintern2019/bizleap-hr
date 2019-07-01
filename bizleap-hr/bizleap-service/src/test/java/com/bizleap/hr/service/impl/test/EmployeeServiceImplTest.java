package com.bizleap.hr.service.impl.test;

import com.bizleap.commons.domain.entity.Address;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.EmployeeService;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(EmployeeServiceImplTest.class);

	@Autowired
	EmployeeService employeeService;

	@Test
	public void testSaveEmployee() {
		Location location = new Location();
		location.setBoId("LOC001");
		location.setName("Yangon");

		Department department = new Department();
		department.setBoId("DEPT001");
		department.setName("BOD");
		department.setLocation(location);
		department.setParentDepartment(new Department("null"));

		Job job = new Job();
		job.setBoId("JOB001");
		job.setJobTitle("CEO");
		job.setSalary(800000);
		job.setDepartment(department);

		Position position = new Position();
		position.setBoId("JOB001-1");
		position.setJob(job);
		position.setReportToList(null);
		position.setReportByList(null);

		Address address = new Address();
		address.setBoId("ADR001");
		address.setPermanentAddress("No.11,zinziyar Str");
		address.setContactAddress("No.11,zinziyar Str");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");

		Employee employee = new Employee();
		employee.setBoId("EMP001");
		employee.setTitle("Mg");
		employee.setFirstName("Aung");
		employee.setLastName("Aung");
		employee.setPosition(position);
		employee.setEntranceDate("30-06-2019");
		employee.setDateOfBirth("06-09-1995");
		employee.setGender("Male");
		employee.setEmail("aung@gmail.com");
		employee.setPhone("0925645652");
		employee.setAddress(address);

		try {
			logger.info("employeeData" + employee.toString());
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
