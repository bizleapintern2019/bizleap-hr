package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.JobService;

public class JobServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(JobServiceImplTest.class);

	@Autowired
	JobService jobService;

//	@Autowired
//	JobTest jobTest;
	@Test
	public void testSaveJob() {
		Job job = new Job();
		job.setBoId("JOB001");
		job.setJobTitle("CEO");
		job.setSalary(800000);
		job.setDepartment(new Department("DEPT001"));

		try {
			jobService.saveJob(job);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}

		logger.info(job);
	}

	@Test
	public void getAllJobs() throws Exception {
		try {
			testJobList(jobService.getAll());
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: " + e);
		}
	}

	public int assertJob(Job job,String boId,String title,int salary){
		if(job.getBoId().equals(boId)) {
			Assert.assertEquals(job.getJobTitle(),title);
			Assert.assertEquals(job.getSalary(),salary);
			return 1;
		}
		return 0;
	}
	
	public void testJobList(List<Job> jobList) throws Exception {
		
		Assert.assertTrue(jobList != null && jobList.size() == 6);
		int successCount = 0;
		for (Job job : jobList) {
			successCount+= assertJob(job,"JOB001","CEO",800000);
			successCount+= assertJob(job,"JOB002","Senior Software Engineer",400000);
			successCount+= assertJob(job,"JOB003","Software Engineer",300000);
			successCount+= assertJob(job,"JOB004","General Manager",400000);
			successCount+= assertJob(job,"JOB005","Technical lead",300000);
			successCount+= assertJob(job,"JOB006","InternShip",40000);
			//Assert.assertTrue(successCount==1);
			//successCount=0;
		}
		Assert.assertTrue(successCount==6);
	}
	
	@Test
	public void testFindByBoId() {
		try {

			List<Job> jobList = jobService.findByBoId("JOB001");
			Assert.assertTrue(CollectionUtils.isNotEmpty(jobList));
			Assert.assertEquals(jobList.get(0).getBoId(),"JOB001");
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}

	@Test
	public void testFindByTitle() {
		try {

			List<Job> jobList = jobService.findByTitle("CEO");
			Assert.assertTrue(CollectionUtils.isNotEmpty(jobList));
			Assert.assertEquals(jobList.get(0).getJobTitle(),"CEO");
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}

	@Test
	public void testFindBySalary() {
		try {

			List<Job> jobList = jobService.findBySalary(800000);
			Assert.assertTrue(CollectionUtils.isNotEmpty(jobList));
			Assert.assertEquals(jobList.get(0).getSalary(),800000);
			
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		}
	}

}
