package com.bizleap.hr.loader;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.hr.loader.impl.DataManagerImpl;


public class LocationTest {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	@Test
	public void parseLocationTest() {
		Location location=Location.parseLocation("LOC001;Yangon;DEPT001,DEPT002");
		Assert.assertEquals("LOC001", location.getBoId());
		Assert.assertEquals("Yangon", location.getName());
		//logger.info("Dept List is "+location.getDepartmentList().get(0).getName());
		Assert.assertEquals("DEPT001",location.getDepartmentList().get(0).getBoId());
		Assert.assertEquals("DEPT002",location.getDepartmentList().get(1).getBoId());
	}
}
