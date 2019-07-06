package com.bizleap.hr.service.impl.test;


import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.PositionService;

import junit.framework.Assert;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(EmployeeServiceImplTest.class);

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	AddressService addressService;

	@Test
	public void testSaveEmployee() throws ServiceUnavailableException {	
		
		Employee employee = new Employee();
		employee.setBoId("EMP0027");
		employee.setTitle("Mg");
		employee.setFirstName("Kyaw");
		employee.setLastName("Kyaw");
		employee.setPosition(positionService.findByBoId("JOB002-1").get(0));
		employee.setEntranceDate("30-06-2019");
		employee.setDateOfBirth("06-09-1995");
		employee.setGender("Male");
		employee.setEmail("aung@gmail.com");
		employee.setPhone("0925645652");
		employee.setAddress(addressService.findByBoId("ADR010").get(0));

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
				Assert.assertTrue(employeeList.size()==25);
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
				Assert.assertTrue(employeeList.size()==2);
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
				Assert.assertTrue(employeeList.size()==2);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByLastName() {
		try {
			List<Employee> employeeList = employeeService.findByLastName("Shein");
			if(!CollectionUtils.isEmpty(employeeList)){
				employeeService.hibernateInitializedList(employeeList);
				Assert.assertTrue(employeeList.size()==25);
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
				Assert.assertTrue(employeeList.size()==14);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
}
