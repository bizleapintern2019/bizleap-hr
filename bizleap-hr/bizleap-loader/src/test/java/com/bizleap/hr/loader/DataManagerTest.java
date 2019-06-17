package com.bizleap.hr.loader;

import java.util.Map;
import org.junit.Test;

import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;
import com.bizleap.commons.domain.entity.Error;

public class DataManagerTest {
	
	@Test
	public void dataManagerTest() {
		
		DataManager dataManager= new DataManagerImpl();
		dataManager.loadData();
		
		AssociationMapper associationMapper = new AssociationMapperImpl(dataManager);
		associationMapper.setUpAssociations();
		
		Map<Integer, Error> errorHashMap = dataManager.getDataLoader().getErrorMap();
		System.out.println(errorHashMap);
	}
}