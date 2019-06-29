package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Location;

public class LocationTest {
	@Test
	public void parseLocationTest() {
		Location.parseLocation("LOC001;Yangon");
	}
}
