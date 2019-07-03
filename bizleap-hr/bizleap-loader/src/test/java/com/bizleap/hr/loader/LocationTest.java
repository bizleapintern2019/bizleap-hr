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

	private List<Location> locationList;

	@Test
	public void parseLocationTest() throws Exception {
		locationList = dataLoader.loadLocation();
		Assert.assertTrue(locationList != null && locationList.size() == 2);

		for (Location location : locationList) {
			switch (location.getBoId()) {
			case "LOC001":
				Assert.assertEquals(location.getName(), "Yangon");
				Assert.assertEquals(location.getDepartmentList().get(0).getBoId(), "DEPT001");
				Assert.assertEquals(location.getDepartmentList().get(1).getBoId(), "DEPT002");
				break;
			case "LOC002":
				Assert.assertEquals(location.getName(), "Mandalay");
				Assert.assertEquals(location.getDepartmentList().get(0).getBoId(), "DEPT003");
				Assert.assertEquals(location.getDepartmentList().get(1).getBoId(), "");
				break;
			default:
				Assert.assertTrue(false);
			}
		}
	}
}
