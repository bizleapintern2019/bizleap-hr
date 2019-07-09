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
	public void testAll() {
		dataManager.load();
		Assert.assertTrue(dataManager.getLocationList() != null && dataManager.getLocationList().size()==2);
		Assert.assertTrue(dataManager.getDepartmentList() != null && dataManager.getDepartmentList().size()==4);
		Assert.assertTrue(dataManager.getJobList() != null && dataManager.getJobList().size()==6);
		Assert.assertTrue(dataManager.getPositionList() != null && dataManager.getPositionList().size()==24);
		Assert.assertTrue(dataManager.getEmployeeList() != null && dataManager.getEmployeeList().size()==24);
		Assert.assertTrue(dataManager.getAddressList() != null && dataManager.getAddressList().size()==24);
	}

}