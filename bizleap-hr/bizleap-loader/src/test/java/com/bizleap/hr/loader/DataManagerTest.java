package com.bizleap.hr.loader;

import org.junit.Test;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	
	@Test
	public void dataTest() throws Exception {
		DataManagerImpl dataManager = new DataManagerImpl();
		dataManager.load();
		//new  AssociationMapperImpl(null).setUpAssociations();
	}
}
