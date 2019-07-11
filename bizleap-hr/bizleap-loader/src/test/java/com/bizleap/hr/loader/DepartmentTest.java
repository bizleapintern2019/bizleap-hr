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

	@Test
	public void testParseDepartment() throws Exception{
		testDepartmentList(dataLoader.loadDepartment());
	}
	
	public int assertDepartment(Department department,String boId,String name,String parentDepartment) {
		
		if(department.getBoId().equals(boId)) {
			Assert.assertEquals(department.getName(), name);
			if(parentDepartment!=null) {
				Assert.assertEquals(department.getParentDepartment().getBoId(), parentDepartment);
			}
			return 1;
		}
		return 0;
	}
	
	public void testDepartmentList(List<Department> departmentList) throws Exception {
		
		Assert.assertTrue(departmentList != null && departmentList.size() == 4);
        int successCount = 0;
        for(Department department : departmentList) {
        	successCount+= assertDepartment(department,"DEPT001","BOD",null);
        	successCount+= assertDepartment(department,"DEPT002","Engineering","DEPT001");
        	successCount+= assertDepartment(department,"DEPT003","Internship","DEPT002");
        	successCount+= assertDepartment(department,"DEPT004","Customer Support","DEPT002");
        	Assert.assertTrue(successCount==1);
        	successCount=0;
        }
	}
}
