package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.hr.loader.DataManagerImpl;

public class DataManagerTest {
	
	//static Company company;
	//static Employee employee;
	
	@Test
	public void dataTest() {
		DataManagerImpl dataManager = new DataManagerImpl();
		System.out.println(dataManager.loadData());
		//AssociationMapperImpl ass=new AssociationMapperImpl(dataManager);
		//ass.setUpAssociations();
	}
}
