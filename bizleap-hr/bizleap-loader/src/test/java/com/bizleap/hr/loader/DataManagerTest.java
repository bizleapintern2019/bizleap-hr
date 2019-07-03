package com.bizleap.hr.loader;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Department;
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
	public void locationTest() {
		dataManager.load();
		Assert.assertTrue(dataManager.getLocationList() != null);
		Assert.assertTrue(dataManager.getLocationList().size() == 2);
		Location location = (findEntityByBoId(dataManager.getLocationList(), "LOC001"));
		Assert.assertEquals("LOC001",location.getBoId());
		Assert.assertTrue(location.getDepartmentList()!=null && location.getDepartmentList().size() < 3);
	}
	
	@Test
	public void departmentTest() {
		dataManager.load();
		Department department=dataManager.getDepartmentList().get(3);
		Assert.assertEquals("DEPT004", department.getBoId());
		Assert.assertEquals("Customer Support",department.getName());
		Assert.assertEquals("DEPT002",department.getParentDepartment().getBoId());
	}
	
	@Test
	public void positionTest() {
		dataManager.load();
		Position position=dataManager.getPositionList().get(1);
		Assert.assertEquals("JOB002-1", position.getBoId());
		Assert.assertEquals("JOB002", position.getJob().getBoId());
		Assert.assertEquals("JOB001-1", position.getReportToList().get(0).getBoId());
		Assert.assertEquals("JOB004-1", position.getReportToList().get(1).getBoId());
	}
}