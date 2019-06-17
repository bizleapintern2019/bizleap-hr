package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	
	@Test
	public void dataTest() throws Exception {
		DataManagerImpl dataManager = new DataManagerImpl();
		dataManager.loadData();
		AssociationMapper association =new AssociationMapperImpl(dataManager);
		association.setUpAssociations(); 
		if(association.getErrorHashMap()!=null)
		System.out.println("\t\t\t\t Linked Error\n"+association.getErrorHashMap());
	}
}
