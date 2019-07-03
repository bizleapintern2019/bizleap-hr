package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.hr.loader.impl.DataManagerImpl;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DepartmentTest extends ServiceTest {
	@Autowired
	private DataLoader dataLoader;

	private List<Department> departmentList;

	@Test
	public void parseDepartmentTest() throws Exception {
		departmentList = dataLoader.loadDepartment();
		Assert.assertTrue(departmentList != null && departmentList.size() == 4);

		for (Department department : departmentList) {
			switch (department.getBoId()) {
			case "DEPT001":
				Assert.assertEquals(department.getName(), "BOD");
				Assert.assertEquals(department.getParentDepartment().getBoId(), " ");
				break;
			case "DEPT002":
				Assert.assertEquals(department.getName(), "Engineering");
				Assert.assertEquals(department.getParentDepartment().getBoId(), "DEPT001");
				break;
			case "DEPT003":
				Assert.assertEquals(department.getName(), "Internship");
				Assert.assertEquals(department.getParentDepartment().getBoId(), "DEPT002");
				break;
			case "DEPT004":
				Assert.assertEquals(department.getName(), "Customer Support");
				Assert.assertEquals(department.getParentDepartment().getBoId(), "DEPT002");
				break;
				
			default:
				Assert.assertTrue(false);
			}
		}
	}
}
