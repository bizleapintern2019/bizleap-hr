package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.impl.DataManagerImpl;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class PositionTest {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	@Test
	public void parsePositionTest() {
		Position position = Position.parsePosition("JOB002-1;JOB002;JOB001-1,JOB004-1");
		Assert.assertEquals("JOB002-1", position.getBoId());
		Assert.assertEquals("JOB002", position.getJob().getBoId());
		//logger.info("Your Position List is "+position.getReportToList().get(0).getBoId());
		Assert.assertEquals("JOB001-1", position.getReportToList().get(0).getBoId());
		Assert.assertEquals("JOB004-1", position.getReportToList().get(1).getBoId());
	}
}
