package com.bizleap.hr.loader;

import org.junit.Test;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	
	//static Company company;
	//static Employee employee;
	
	@Test
	public void dataLoad() throws Exception {
		DataManager dataManager=new DataManagerImpl();
		dataManager.loadData();
		AssociationMapper assocaiationMapper=new AssociationMapperImpl(dataManager);
		assocaiationMapper.setUpAssociations();
		System.out.println("\n\t\t\t Linked Error is the line below this source : "+"\n"+assocaiationMapper.getErrorHashMap());
	}
}
