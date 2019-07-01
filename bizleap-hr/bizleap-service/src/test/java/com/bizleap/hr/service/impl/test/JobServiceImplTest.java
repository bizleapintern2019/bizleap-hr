package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.JobService;

public class JobServiceImplTest {
	
	private Logger logger = Logger.getLogger(JobServiceImplTest.class);

	@Autowired
	JobService jobService;
	
	@Test
	public void jobServiceTest() {
		Job job = new Job();
		job.setBoId("JOB001");
		job.setJobTitle("CEO");
		job.setSalary(800000);
		job.setDepartment(new Department("DEPT001"));
		
		try {
			jobService.saveJob(job);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(job);
	}
}
