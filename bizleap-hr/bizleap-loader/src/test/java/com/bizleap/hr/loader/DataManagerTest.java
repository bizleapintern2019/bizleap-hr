package com.bizleap.hr.loader;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class DataManagerTest extends ServiceTest {
	@Autowired
	private DataManager dataManager;
	@Test
	public void dataLoad() throws Exception {
		dataManager.load();
	}
}
