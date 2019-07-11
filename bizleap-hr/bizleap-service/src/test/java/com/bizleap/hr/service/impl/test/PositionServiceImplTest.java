package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.PositionService;

public class PositionServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(PositionServiceImplTest.class);

	@Autowired
	PositionService positionService;

	@Test
	public void testSavePosition() {
		List<Position> reportToList = new ArrayList<Position>();
		reportToList.add(new Position("JOB001-2"));

		Position position = new Position();
		position.setBoId("JOB001-1");
		position.setJob(new Job("JOB001"));
		position.setReportToList(reportToList);
		position.setReportByList(reportToList);
		try {
			positionService.savePosition(position);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		logger.info(position);
	}

	@Test
	public void testGetAllPosition() throws Exception {
		try {
			// logger.info(positionService.getAll());
			testPositionList(positionService.getAll());
		} catch (ServiceUnavailableException e) {
			logger.info("The Message is " + e);
		}
	}

	public int assertPosition(Position position, String boId, String reportTo) {
		if (position.getBoId().equals(boId)) {
			if (reportTo != null) {
				String[] reportToItems = reportTo.split(",");
				for (int i = 0; i < reportToItems.length; i++) {
					Assert.assertEquals(position.getReportToList().get(i).getBoId(), reportToItems[i]);
				}
			}
			return 1;
		}
		return 0;
	}

	public void testPositionList(List<Position> positionList) throws Exception {
		Assert.assertTrue(positionList != null && positionList.size() == 24);
		int successCount = 0;
		for (Position position : positionList) {
			successCount += assertPosition(position, "JOB001-1", null);
			successCount += assertPosition(position, "JOB002-1", "JOB001-1,JOB004-1");
			successCount += assertPosition(position, "JOB003-4", "JOB001-1,JOB004-1,JOB002-1");
			successCount += assertPosition(position, "JOB006-9", "JOB002-3,JOB002-4,JOB003-1,JOB003-4");
			successCount += assertPosition(position, "JOB002-3", "JOB001-1,JOB004-1,JOB005-1");
			successCount += assertPosition(position, "JOB003-1", "JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3");
			successCount += assertPosition(position, "JOB004-1", null);
			successCount += assertPosition(position, "JOB002-2", "JOB001-1,JOB004-1,JOB005-1");
			successCount += assertPosition(position, "JOB003-2",
					"JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-6", "JOB002-3,JOB002-4,JOB002-2,JOB003-2");
			successCount += assertPosition(position, "JOB002-4", "JOB001-1,JOB004-1");
			successCount += assertPosition(position, "JOB006-1", "JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-2", "JOB002-3,JOB002-4,JOB003-3,JOB003-5");
			successCount += assertPosition(position, "JOB003-3", "JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3");
			successCount += assertPosition(position, "JOB006-3", "JOB002-3,JOB002-4,JOB003-3");
			successCount += assertPosition(position, "JOB006-11", "JOB002-3,JOB002-4,JOB003-3,JOB003-5");
			successCount += assertPosition(position, "JOB003-5", "JOB001-1,JOB004-1,JOB002-1");
			successCount += assertPosition(position, "JOB005-1", "JOB001-1,JOB004-1");
			successCount += assertPosition(position, "JOB006-4", "JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-5", "JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-7", "JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-8", "JOB002-3,JOB002-4");
			successCount += assertPosition(position, "JOB006-10", "JOB002-3,JOB002-4,JOB003-2,JOB003-4");
			successCount += assertPosition(position, "JOB006-12", "JOB002-3,JOB002-4");
			Assert.assertTrue(successCount == 1);
			successCount = 0;
		}
	}

	@Test
	public void testFindByboId() {
		try {
			Position position = positionService.findByBoId("JOB001-1");
			Assert.assertEquals("JOB001-1", position.getBoId());
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindPostionByEmployeeBoId() {
		try {
			Position position = positionService.findPositionByEmployeeBoId("EMP001");
			logger.info(position);
		} catch (ServiceUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
