package com.bizleap.hr.loader;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	@Test
	public void dataManagerTest() {	
	DataManager dataManager= new DataManagerImpl();
		dataManager.load();
	}
}
