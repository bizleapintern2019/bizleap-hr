package com.bizleap.hr.loader;

import org.junit.Test;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Employee;

public class EmployeeTest {
	
	@Test
	public void parseEmployeeTest() {
		Employee employee=Employee.parseEmployee("EMP001;U;Nyunt;Than;JOB001-1;18-01-2014;07-07-1962;Male;nyuntthan@gmail.com;01511522;ADR001");
		Assert.assertEquals("EMP001", employee.getBoId());
		Assert.assertEquals("U",employee.getTitle());
		Assert.assertEquals("Nyunt",employee.getFirstName());
		Assert.assertEquals("Than",employee.getLastName());
		Assert.assertEquals("JOB001-1",employee.getPosition().getBoId());
		Assert.assertEquals("18-01-2014",employee.getEntranceDate());
		Assert.assertEquals("07-07-1962",employee.getDateOfBirth());
		Assert.assertEquals("Male",employee.getGender());
		Assert.assertEquals("nyuntthan@gmail.com",employee.getEmail());
		Assert.assertEquals("01511522",employee.getPhone());
		Assert.assertEquals("ADR001",employee.getAddress().getBoId());
	}
}
