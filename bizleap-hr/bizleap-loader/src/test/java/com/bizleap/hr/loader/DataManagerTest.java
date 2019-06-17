package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	
	@Test
	public void dataLoad() throws Exception {
		DataManager dataManager = new DataManagerImpl();
		dataManager.loadData();
		AssociationMapper associationMapper = new AssociationMapperImpl(dataManager);
		associationMapper.setUpAssociations();
		if(associationMapper.getErrorHashMap() != null)
		System.out.println("\t\t\t\t\t\tLinkedERROR\n"+associationMapper.getErrorHashMap());
	}
}
