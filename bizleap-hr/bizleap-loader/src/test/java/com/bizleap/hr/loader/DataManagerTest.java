package com.bizleap.hr.loader;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DataManagerTest extends ServiceTest {

	@Autowired
	DataManager dataManager;
	
	private Location findEntityByBoId(List<Location> entityList,String boId) {
		for(Location entity : entityList) {
			if(entity.getBoId().equals(boId)) {
				return entity;
			}
		}
		return null;
	}
	
	@Test
	public void loadTest() throws Exception {
		dataManager.load();
	}
	
	@Test
	public void locationMapperTest() {
		dataManager.load();
		for(Location location : dataManager.getLocationList()) {
			Assert.assertTrue(location.getDepartmentList().size()<3 && location.getDepartmentList()!=null);
		}
	}
	
	@Test
	public void departmentMapperTest() {
		dataManager.load();
		Department department = dataManager.getDepartmentList().get(0);
		Assert.assertTrue(department.getJobList().get(0).getBoId().equals("JOB001"));
	}
	
	@Test
	public void parentDepartmentTest() {
		dataManager.load();
		Department department = dataManager.getDepartmentList().get(1);
		Assert.assertTrue(department.getParentDepartment().getBoId().equals("DEPT001"));
	}
	
	@Test
	public void jobMapperTest() {
		dataManager.load();
		Job job = dataManager.getJobList().get(0);
		Assert.assertTrue(job.getPositionList().get(0).getBoId().equals("JOB001-1"));
	}
	
	@Test
	public void positionMapperTest() {
		dataManager.load();
		Position position = dataManager.getPositionList().get(0);
		Assert.assertTrue(position.getEmployee().getBoId().equals("EMP001"));
	}
	
	@Test
	public void employeeMapperTest() {
		dataManager.load();
		Employee employee = dataManager.getEmployeeList().get(0);
		Assert.assertTrue(employee.getAddress().getBoId().equals("ADR001"));
	}
	
	@Test
	public void reportToTest() {
		dataManager.load();
		Position position = dataManager.getPositionList().get(1);
		Position reportTo = position.getReportToList().get(0);
		Assert.assertEquals("JOB001-1",reportTo);
	}
	
	@Test
	public void reportByTest() {
		dataManager.load();
		Position position = dataManager.getPositionList().get(1);
		Position reportBy = position.getReportByList().get(0);
		Assert.assertEquals("JOB003-4",reportBy);
	}
	
	@Test
	public void allTest() {
		locationMapperTest();
		departmentMapperTest();
		parentDepartmentTest();
		jobMapperTest();
		positionMapperTest();
		reportToTest();
		reportByTest();
		employeeMapperTest();
	}
}