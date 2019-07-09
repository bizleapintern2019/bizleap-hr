package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.junit.Assert;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class LocationTest extends ServiceTest {

	@Autowired
	private DataLoader dataLoader;

	@Test
	public void testParseLocation() throws Exception{
		testLocationList(dataLoader.loadLocation());
	}
	
	public int assertLocation(Location location,String boId,String name,String departmentList) {
		if(location.getBoId().equals(boId)) {
			Assert.assertEquals(location.getName(), name);
			String[] departmentItems = departmentList.split(",");
			for(int i=0;i<departmentItems.length;i++) {
				Assert.assertEquals(location.getDepartmentList().get(i).getBoId(), departmentItems[i]);
			}
			return 1;
		}
		return 0;
	}
	
	public void testLocationList(List<Location> locationList) throws Exception {
		Assert.assertTrue(locationList != null && locationList.size() == 2);
		int successCount=0;
		for(Location location : locationList) {
			successCount+=assertLocation(location,"LOC001","Yangon","DEPT001,DEPT002");
			successCount+=assertLocation(location,"LOC002","Mandalay","DEPT003,DEPT004");
			Assert.assertTrue(successCount==1);
			successCount=0;
		}
	}
}
