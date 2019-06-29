package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Department;

public class DepartmentTest {
	@Test
	public void parseDepartmentTest() {
		Department.parseDepartment("DEPT002; Engineering; LOC001; DEPT001");
	}
}
