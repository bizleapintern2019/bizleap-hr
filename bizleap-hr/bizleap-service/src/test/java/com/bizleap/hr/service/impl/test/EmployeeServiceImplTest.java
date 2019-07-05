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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
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
		//location.setDepartmentList(departmentList);

		Department department = new Department();
		department.setBoId("DEPT001");
		department.setName("BOD");
		department.setLocation(location);
		department.setParentDepartment(null);
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department);
		
		location.setDepartmentList(departmentList);

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
			logger.error(e);
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		logger.info(employee);
	}
	
	@Test
	public void testGetAll() {
		try {
			List<Employee> employeeList = employeeService.getAll();
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				logger.info("All employee list in Service Test: "+employeeList);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByBoId() {
		try {
			List<Employee> employeeList = employeeService.findByBoId("EMP001");
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				logger.info("Employee of boId "+"EMP001 is: "+employeeList.get(0).getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByFirstName() {
		try {
			List<Employee> employeeList = employeeService.findByFirstName("Kaung");
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				logger.info("First Name Kaung Employee are "+employeeList);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByLastName() {
		try {
			List<Employee> employeeList = employeeService.findByLastName("Tun");
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				logger.info("Last Name Tun Employee are "+employeeList);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByGender() {
		try {
			List<Employee> employeeList = employeeService.findByGender("Male");
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				logger.info("Male employee are "+employeeList);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
}
