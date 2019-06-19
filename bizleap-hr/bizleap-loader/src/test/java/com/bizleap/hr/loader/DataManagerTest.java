package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.hr.loader.impl.DataManagerImpl;


public class DataManagerTest {
	@Test
	public void DataTest() throws Exception {
		DataManager dataManager= new DataManagerImpl();
		dataManager.load();
	}
}
