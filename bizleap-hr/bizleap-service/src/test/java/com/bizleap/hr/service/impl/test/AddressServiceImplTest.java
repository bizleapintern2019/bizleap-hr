package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;


public class AddressServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(AddressServiceImplTest.class);

	@Autowired
	AddressService addressService;

	@Test
	public void testSaveAddress() {
		
		Address address = new Address();
		address.setBoId("ADR0030");
		address.setPermanentAddress("No.11,blah");
		address.setContactAddress("yadanar Str");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");

		try {
			addressService.saveAddress(address);
		} 
		catch (IOException e) {
			logger.error(e);
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}
		
		logger.info(address);
	}
	public static int testAssertLocation(Location location,String boId, String name) {
		if(location.getBoId().equals(boId)) {
			Assert.assertEquals(name, location.getName());
			return 1;
		}
		return 0;
	}
	
	public static void testLocationInDept(Location location) {
		Assert.assertTrue(location != null);
		int successCount=0;
		successCount += testAssertLocation(location,"LOC001", "Yangon");
		successCount += testAssertLocation(location,"LOC002", "Mandalay");
		Assert.assertTrue(successCount==1);
	}
	
	public static int testAssertDept(Department department,String boId,String name,String parentBoId) {
		if(department.getBoId().equals(boId)) {
			Assert.assertEquals(name, department.getName());
			Assert.assertEquals(parentBoId, department.getParentDepartment().getBoId());
			return 1;
		}
		return 0;
	}
	
	public static void testDeptInJob(Department department) {
		Assert.assertTrue(department != null);
		int successCount=0;
		successCount += testAssertDept(department, "DEPT001", "BOD", null);
		successCount += testAssertDept(department, "DEPT002", "Engineering", "DEPT001");
		successCount += testAssertDept(department, "DEPT003", "Internship", "DEPT002");
		successCount += testAssertDept(department, "DEPT004", "Customer Support", "DEPT002");
		Assert.assertTrue(successCount==1);
	}
	
	public static int testAssertJob(Job job, String boId,String title, int salary, String deptBoId) {
		if(job.getBoId().equals(boId)) {
			Assert.assertEquals(title,job.getJobTitle());
			Assert.assertEquals(salary, job.getSalary());
			Assert.assertEquals(deptBoId, job.getDepartment().getBoId());
			return 1;
		}
		return 0;
	}
	
	public static void testJobInPosition(Job job) {
		Assert.assertTrue(job != null);
		int successCount=0;
		successCount += testAssertJob(job, "JOB001", "CEO", 800000, "DEPT001");
		successCount += testAssertJob(job, "JOB002", "Senior Software Engineer", 400000, "DEPT002");
		successCount += testAssertJob(job, "JOB003", "Software Engineer", 300000, "DEPT002");
		successCount += testAssertJob(job, "JOB004", "General Manager", 400000, "DEPT002");
		successCount += testAssertJob(job, "JOB005", "Technical lead", 300000, "DEPT002");
		successCount += testAssertJob(job, "JOB006", "InternShip", 40000, "DEPT003");
		Assert.assertTrue(successCount==1);
	}
	
	public static int testAssertPosition(Position position,String boId, String jobBoId) {
		if(position.getBoId().equals(boId)) {
			Assert.assertEquals(jobBoId, position.getJob().getBoId());
			return 1;
		}
		return 0;
	}
	
	public static void testPositionInEmployee(Position position) {
		Assert.assertTrue(position != null);
		int successCount=0;
		successCount += testAssertPosition(position, "JOB001-1", "JOB001");
		successCount += testAssertPosition(position, "JOB002-1", "JOB002");
		successCount += testAssertPosition(position, "JOB002-2", "JOB002");
		successCount += testAssertPosition(position, "JOB002-3", "JOB002");
		successCount += testAssertPosition(position, "JOB002-4", "JOB002");
		successCount += testAssertPosition(position, "JOB003-1", "JOB003");
		successCount += testAssertPosition(position, "JOB003-2", "JOB003");
		successCount += testAssertPosition(position, "JOB003-3", "JOB003");
		successCount += testAssertPosition(position, "JOB003-4", "JOB003");
		successCount += testAssertPosition(position, "JOB003-5", "JOB003");
		successCount += testAssertPosition(position, "JOB004-1", "JOB004");
		successCount += testAssertPosition(position, "JOB005-1", "JOB005");
		successCount += testAssertPosition(position, "JOB006-1", "JOB006");
		successCount += testAssertPosition(position, "JOB006-2", "JOB006");
		successCount += testAssertPosition(position, "JOB006-3", "JOB006");
		successCount += testAssertPosition(position, "JOB006-4", "JOB006");
		successCount += testAssertPosition(position, "JOB006-5", "JOB006");
		successCount += testAssertPosition(position, "JOB006-6", "JOB006");
		successCount += testAssertPosition(position, "JOB006-7", "JOB006");
		successCount += testAssertPosition(position, "JOB006-8", "JOB006");
		successCount += testAssertPosition(position, "JOB006-9", "JOB006");
		successCount += testAssertPosition(position, "JOB006-10", "JOB006");
		successCount += testAssertPosition(position, "JOB006-11", "JOB006");
		successCount += testAssertPosition(position, "JOB006-12", "JOB006");
		Assert.assertTrue(successCount==1);
	}
	
	public static int testAssertEmployee(Employee employee,String boId,String firstName,String lastname,String gender) {
		if(employee.getBoId().equals(boId)) {
			Assert.assertEquals(firstName, employee.getFirstName());
			Assert.assertEquals(lastname, employee.getLastName());
			Assert.assertEquals(gender, employee.getGender());
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
	
	public static int testAssertAddress(Address address,String boId,String city,String state, String country) {
		if(address.getBoId().equals(boId)) {
			Assert.assertEquals(city, address.getCity());
			Assert.assertEquals(state, address.getState());
			Assert.assertEquals(country, address.getCountry());
			return 1;
		}
		return 0;
	}
	
	public static void testAddress(Address address) {
		Assert.assertTrue(address != null);
		int successCount=0;
		successCount += testAssertAddress(address, "ADR001", "Yangon", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR002", "Mingaladone Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR003", "Yangon", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR004", "Yangon", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR005", "Bago", "Bago", "Myanmar");
		successCount += testAssertAddress(address, "ADR006", "Bahan Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR007", "San Chaung Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR008", "Hmawbi Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR009", "Insein Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR010", "Tharkayta Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR011", "North Dagon Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR012", "Mingaladon Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR013", "Tharkayta Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR014", "Hlaing Thar Yar Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR015", "Hlaing Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR016", "North Okalapa Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR017", "Insein Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR018", "Tharkayta Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR019", "Mingalartownnyunt Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR020", "North Okalapa Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR021", "South Dagon Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR022", "Mingalartownnyunt Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR023", "Mayangone Township", "Yangon", "Myanmar");
		successCount += testAssertAddress(address, "ADR024", "Thingangyun Township", "Yangon", "Myanmar");
		Assert.assertTrue(successCount==1);
	}
	
	@Test
	public void testAddressList() throws ServiceUnavailableException {
		for(Address address:addressService.getAll()) {
			testAddress(address);
			testEmployee(address.getEmployee());
			testPositionInEmployee(address.getEmployee().getPosition());
			testJobInPosition(address.getEmployee().getPosition().getJob());
			testDeptInJob(address.getEmployee().getPosition().getJob().getDepartment());
			testLocationInDept(address.getEmployee().getPosition().getJob().getDepartment().getLocation());
		}
	}
	@Test
	public void testGetAll() {
		try {
			List<Address> addressList = addressService.getAll();
			Assert.assertTrue(CollectionUtils.isNotEmpty(addressList));
			Assert.assertTrue(addressList.size()==24);
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
//	@Test
//	public void testFindByBoId() {
//		try {
//			Address address = addressService.findByBoId("ADR006");
//			Assert.assertTrue(address!=null);
//		} catch (ServiceUnavailableException e) {
//			logger.error("In Service Test: "+e);
//		}
//	}
//	
//	@Test
//	public void testFindByCity() {
//		try {
//			List<Address> addressList = addressService.findByCity("Bago");
//				Assert.assertTrue(addressList.size()==1);
//		} catch (ServiceUnavailableException e) {
//			logger.error("In Service Test: "+e);
//		}
//	}
//	
//	@Test
//	public void testFindByState() {
//		try {
//			List<Address> addressList = addressService.findByState("Yangon");
//				Assert.assertTrue(addressList.size()==1);
//		} catch (ServiceUnavailableException e) {
//			logger.error("In Service Test: "+e);
//		}
//	}
}