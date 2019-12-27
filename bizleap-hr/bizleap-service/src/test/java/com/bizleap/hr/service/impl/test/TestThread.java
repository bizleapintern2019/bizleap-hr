package com.bizleap.hr.service.impl.test;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.DataService;

public class TestThread extends Thread {
	private int callCount;
	private int sleepCount;
	private DataService dataService;

	private Logger logger = Logger.getLogger(TestThread.class);

	public TestThread(String name, int callCount, int sleepCount, DataService dataService) {
		setName(name);
		this.callCount = callCount;
		this.dataService = dataService;
		this.sleepCount = sleepCount;
	}

	public int getSleepCount() {
		return sleepCount;
	}

	public void setSleepCount(int sleepCount) {
		this.sleepCount = sleepCount;
	}

	public int getCallCount() {
		return callCount;
	}

	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void run() {
		while(callCount-- >= 0){
			try {
				logger.info("Entity Service Test: "+dataService);
				List<AbstractEntity> entityList = dataService.getAllEntity();
				if(CollectionUtils.isNotEmpty(entityList))
					logger.info("Entity Found: "+entityList.size());
				else logger.info("Entity Found: 0");
			} catch (ServiceUnavailableException e) {
				logger.error(e.getMessage());
			}
			try {
				Thread.sleep(getSleepCount());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}
		logger.info("Completed: "+getName());
	}
}
