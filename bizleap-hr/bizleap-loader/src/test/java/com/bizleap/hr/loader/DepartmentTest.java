package com.bizleap.hr.loader;

import org.junit.Test;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Department;

public class DepartmentTest {
	
	@Test
	public void parseDepartmentTest() {
		Department department=Department.parseDepartment("DEPT002; Engineering; DEPT001");
		Assert.assertEquals("DEPT002", department.getBoId());
		Assert.assertEquals(" Engineering", department.getName());
		Assert.assertEquals(" DEPT001", department.getParentDepartment().getBoId());
	}
}
