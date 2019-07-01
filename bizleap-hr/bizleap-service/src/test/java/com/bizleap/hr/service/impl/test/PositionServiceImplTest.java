package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.PositionService;


public class PositionServiceImplTest {
	
	private Logger logger = Logger.getLogger(PositionServiceImplTest.class);

	@Autowired
	PositionService positionService;
	
	@Test
	public void positionServiceTest() {
		Position position = new Position();
		position.setBoId("JOB001-1");
		position.setJob(new Job("JOB001"));
		position.setReportByList(null);
		position.setReportToList(null);
		try {
			positionService.savePosition(position);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(position);
	}

}
