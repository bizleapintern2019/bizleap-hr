package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Employee;

public class EmployeeTest {
	@Test
	public void parseEmployeeTest() {
		Employee.parseEmployee("EMP001U;Nyunt;Than;JOB001-1;18-01-2014;07-07-1962;Male;nyuntthan@gmail.com;01511522;ADR001");
	}
}
