package com.bizleap.hr.loader;

import java.util.HashMap;

import org.junit.Test;

import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataManagerImpl;


public class DataManagerTest {
	@Test
	public void DataTest() throws Exception {
		DataManager dataManager= new DataManagerImpl();
		dataManager.loadData();
		AssociationMapper associationMapper = new AssociationMapperImpl(dataManager);
		associationMapper.setUpAssociations();
		HashMap<Integer, ErrorCollection> errorHashMap = associationMapper.getHashMap();
		System.out.println(errorHashMap);
	}
}
