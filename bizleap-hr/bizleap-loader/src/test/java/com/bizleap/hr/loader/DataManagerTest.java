package com.bizleap.hr.loader;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {
	private Logger logger =Logger.getLogger(DataManagerTest.class);
	@Test
	public void dataManagerTest() {
		
	DataManager dataManager= new DataManagerImpl();
		dataManager.load();
		//logger.info("Printing hashmap :"+dataManager.getErrorCollector().getErrorHashMap());
	}
}
