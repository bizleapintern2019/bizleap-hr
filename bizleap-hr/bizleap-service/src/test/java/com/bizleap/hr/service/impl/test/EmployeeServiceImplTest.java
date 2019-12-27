package com.bizleap.hr.service.impl.test;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.PositionService;

import org.junit.Assert;

import java.io.IOException;
import java.util.List;

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
		employee.setPosition(positionService.findByBoId("JOB002-1"));
		employee.setEntranceDate("30-06-2019");
		employee.setDateOfBirth("06-09-1995");
		employee.setGender("Male");
		employee.setEmail("aung@gmail.com");
		employee.setPhone("0925645652");
		employee.setAddress(addressService.findByBoId("ADR010"));

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
	
	public static int testAssertEmployee(Employee employee,String boId,String firstName,String lastname,String gender) {
		if(employee.getBoId().equals(boId)) {
			Assert.assertEquals(firstName, employee.getFirstName());
			Assert.assertEquals(lastname, employee.getLastName());
			Assert.assertEquals(gender, employee.getGender());
			Assert.assertTrue(employee.getAddress()!= null);
			Assert.assertTrue(employee.getPosition()!=null);
			return 1;
		}
		return 0;
	}
	
	public static void testEmployee(Employee employee) {
		Assert.assertTrue(employee != null);
		int successCount=0;
		successCount += testAssertEmployee(employee, "EMP001", "Nyunt", "Than", "Male");
		successCount += testAssertEmployee(employee, "EMP002", "Nyan", "Shein", "Male");
		successCount += testAssertEmployee(employee, "EMP003", "Soe", "Min Htike", "Male");
		successCount += testAssertEmployee(employee, "EMP004", "So", "Pyai Aung", "Male");
		successCount += testAssertEmployee(employee, "EMP005", "Ye", "Yint Thu", "Male");
		successCount += testAssertEmployee(employee, "EMP006", "Phyu", "Mon Thant", "Female");
		successCount += testAssertEmployee(employee, "EMP007", "Kaung", "San Kyaw", "Male");
		successCount += testAssertEmployee(employee, "EMP008", "Htet", "Htet San", "Female");
		successCount += testAssertEmployee(employee, "EMP009", "Saung", "Hnin Phyu", "Female");
		successCount += testAssertEmployee(employee, "EMP010", "Hnin", "Ei Hlaing", "Female");
		successCount += testAssertEmployee(employee, "EMP011", "Ye", "Min Ko", "Male");
		successCount += testAssertEmployee(employee, "EMP012", "Khaing", "Su Thiri", "Female");
		successCount += testAssertEmployee(employee, "EMP013", "Shine", "Wanna", "Male");
		successCount += testAssertEmployee(employee, "EMP014", "Nyan", "Lin Htet", "Male");
		successCount += testAssertEmployee(employee, "EMP015", "Thu", "Ya Oo", "Male");
		successCount += testAssertEmployee(employee, "EMP016", "Kaung", "Pyae Sone Tun", "Male");
		successCount += testAssertEmployee(employee, "EMP017", "Soe", "Min Thein", "Male");
		successCount += testAssertEmployee(employee, "EMP018", "Ya", "Mone Zin", "Female");
		successCount += testAssertEmployee(employee, "EMP019", "Kay", "Zin Han", "Female");
		successCount += testAssertEmployee(employee, "EMP020", "Su", "Pyae Naing", "Female");
		successCount += testAssertEmployee(employee, "EMP021", "Saw", "Sandi Tin", "Female");
		successCount += testAssertEmployee(employee, "EMP022", "San", "Thinzar Linn", "Female");
		successCount += testAssertEmployee(employee, "EMP023", "Khin", "Chanmyae Thu", "Female");
		successCount += testAssertEmployee(employee, "EMP024", "Thi", "Han Hein", "Male");
		Assert.assertTrue(successCount==1);
	}
	
	@Test
	public void testEmployeeList() throws ServiceUnavailableException {
		for(Employee employee : employeeService.getAll()) {
			testEmployee(employee);
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			List<Employee> employeeList = employeeService.getAll();
				logger.info(employeeList);
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
}