package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.hr.loader.impl.DataManagerImpl;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class JobTest extends ServiceTest {
	@Autowired
	private DataLoader dataLoader;

	private List<Job> jobList;

	@Test
	public void parseJobTest() throws Exception {
		jobList = dataLoader.loadJob();
		Assert.assertTrue(jobList != null && jobList.size() == 6);

		for (Job job : jobList) {
			switch (job.getBoId()) {
			case "JOB001":
				Assert.assertEquals(job.getJobTitle(), "CEO");
				Assert.assertEquals(job.getSalary(), 800000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT001");
				break;
			case "JOB002":
				Assert.assertEquals(job.getJobTitle(), "Senior Software Engineer");
				Assert.assertEquals(job.getSalary(), 400000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT002");
				break;
			case "JOB003":
				Assert.assertEquals(job.getJobTitle(), "Software Engineer");
				Assert.assertEquals(job.getSalary(), 300000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT002");
				break;
			case "JOB004":
				Assert.assertEquals(job.getJobTitle(), "General Manager");
				Assert.assertEquals(job.getSalary(), 400000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT002");
				break;
			case "JOB005":
				Assert.assertEquals(job.getJobTitle(), "Technical lead");
				Assert.assertEquals(job.getSalary(), 300000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT002");
				break;
			case "JOB006":
				Assert.assertEquals(job.getJobTitle(), "InternShip");
				Assert.assertEquals(job.getSalary(), 40000);
				Assert.assertEquals(job.getDepartment().getBoId(), "DEPT003");
				break;

			default:
				Assert.assertTrue(false);
			}
		}

	}
}
