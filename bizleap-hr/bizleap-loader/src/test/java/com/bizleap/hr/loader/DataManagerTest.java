package com.bizleap.hr.loader;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DataManagerTest extends ServiceTest {
	
	@Autowired
	DataManager dataManager;
	
	private AbstractEntity  findEntityByBoId(List<AbstractEntity> entityList,String boId) {
		for(AbstractEntity entity : entityList) {
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
	
//	public void locationTest() {
//		dataManager.load();
//		dataManager.getDepartmentList();
//		Assert.assertTrue(dataManager.getLocationList() != null);
//		Assert.assertTrue(dataManager.getLocationList().size()==2);
//		Location location = (Location) (findEntityByBoId(dataManager.getLocationList(), "LOC001"));
//		Assert.assertEquals("LOC001",location.getBoId());
//	}
}
