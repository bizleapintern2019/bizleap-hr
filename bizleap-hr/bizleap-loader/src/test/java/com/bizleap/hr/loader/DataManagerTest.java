package com.bizleap.hr.loader;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Test;


import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;


public class DataManagerTest {
	@Test
	public void dataLoad() throws Exception {
		
		DataManager dataManager = new DataManagerImpl();
		dataManager.load();
	}
}
