package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.hr.loader.impl.test.ServiceTest;
import com.bizleap.hr.loader.DataManager;

public class DataManagerTest extends ServiceTest{
	
	@Autowired
	DataManager dataManager;
	
	@Test
	public void dataManagerTest() throws Exception {
		
		dataManager.load();
	}
}