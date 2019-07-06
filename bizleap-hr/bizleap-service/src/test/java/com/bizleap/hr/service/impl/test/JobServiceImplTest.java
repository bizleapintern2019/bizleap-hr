package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.JobService;

public class JobServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(JobServiceImplTest.class);

	@Autowired
	JobService jobService;

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
	public void getAllJobs() {
		try {
			logger.info("i'm here");
			List<Job> jobList = jobService.getAll();
			
			if (!CollectionUtils.isEmpty(jobList)) {
				jobService.hibernateInitializedList(jobList);
				logger.info("All employee list in Service Test: " + jobList);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: " + e);
		}
	}

	@Test
	public void testFindByBoId() {
		try {

			List<Job> jobList = jobService.findByBoId("JOB001");

			if (!CollectionUtils.isEmpty(jobList)) {
				jobService.hibernateInitializedList(jobList);
				logger.info("Location Name: " + jobList.get(0).getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.info(e);
		}
	}

	@Test
	public void testFindByTitle() {
		try {

			List<Job> jobList = jobService.findByTitle("CEO");

			if (!CollectionUtils.isEmpty(jobList)) {
				jobService.hibernateInitializedList(jobList);
				logger.info("Location Name: " + jobList.get(0).getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.info(e);
		}
	}

	@Test
	public void testFindBySalary() {
		try {

			List<Job> jobList = jobService.findBySalary(800000);

			if (!CollectionUtils.isEmpty(jobList)) {
				jobService.hibernateInitializedList(jobList);
				logger.info("Location Name: " + jobList.get(0).getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.info(e);
		}
	}
}
