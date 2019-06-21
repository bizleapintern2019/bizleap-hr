package com.bizleap.hr.loader;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DataManagerTest extends ServiceTest {
	private Logger logger = Logger.getLogger(DataManagerTest.class);

	@Autowired
	DataManager dataManager;

	@Autowired
	ErrorCollector errorCollector;

	@Test
	public void dataManagerTest() {
		dataManager.load();
		logger.info("Printing hashmap :" + errorCollector.getErrorHashMap());
	}
}
