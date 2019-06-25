package com.bizleap.hr.loader;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DataManagerTest extends ServiceTest {
	
	@Autowired
	private DataManager dataManager;
	
	private Logger logger=Logger.getLogger(DataManagerTest.class);
	@Test
	public void dataLoad() throws Exception {
		logger.info("Saving success");
		dataManager.load();
	}
}
