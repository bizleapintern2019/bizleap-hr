package com.bizleap.hr.loader;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {

	private Logger logger = Logger.getLogger(DataManagerTest.class);
	
	@Test
	public void DataTest() {
		DataManager dataManager = new DataManagerImpl();
		dataManager.load();
		logger.info("Result hashmap: " + dataManager.getErrorHandler().getErrorMap());
		//logger.info("test logger");
	}
}