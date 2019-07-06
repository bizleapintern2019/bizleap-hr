package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
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
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(position);
	}
	
	@Test
	public void testgetAllPosition(){
		try {
			List<Position> positionList =positionService.getAll();
			if(!CollectionUtils.isEmpty(positionList))
				logger.info(positionList);
			
		} catch (ServiceUnavailableException e) {
			logger.info("The Message is "+e);
		}
	}
	
	@Test
	public void testFindByboId(){
		try {
			List<Position> positionList = positionService.findByBoId("JOB003-4");
			if (!CollectionUtils.isEmpty(positionList))
				logger.info("Position BoId: " + positionList.get(0).getBoId()+"Employee Name: "
						+positionList.get(0).getEmployee().getFirstName()+" "
						+positionList.get(0).getEmployee().getLastName()
						+"ReportToList :"+positionList.get(0).getReportToList().get(0).getBoId()
						+"ReportByList :"+positionList.get(0).getReportByList().get(0).getBoId()
						+"ReportByList :"+positionList.get(0).getReportByList().get(1).getBoId());
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
	}
}
