package com.bizleap.hr.loader;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataLoaderImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {

	@Test
	public void dataManagerTest() {
		DataManager dataManager= new DataManagerImpl();
		dataManager.loadData();
		AssociationMapper associationMapper = new AssociationMapperImpl(dataManager);
		associationMapper.setUpAssociations();
		//Map<Integer, ErrorCollection> errorHashMap = dataManager.getDataLoader().getErrorHashMap();
		System.out.println(dataManager.getDataLoader().getErrorHashMap());
	}
}
