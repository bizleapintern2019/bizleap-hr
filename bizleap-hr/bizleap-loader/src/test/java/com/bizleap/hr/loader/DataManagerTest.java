package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.hr.loader.impl.test.ServiceTest;;

public class DataManagerTest extends ServiceTest{
	@Autowired
	DataManager dataManager;
	@Test
	public void dataManagerTest() {	
		dataManager.load();
	}
}
