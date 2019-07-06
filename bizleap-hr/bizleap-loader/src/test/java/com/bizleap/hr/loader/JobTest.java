package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.hr.loader.impl.DataManagerImpl;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class JobTest extends ServiceTest {
	@Autowired
	private DataLoader dataLoader;

	private List<Job> jobList;

	public int assertJob(Job job,String boId,String title,int salary,String deptBoId){
		if(job.getBoId().equals(boId)) {
			Assert.assertEquals(job.getJobTitle(),title);
			Assert.assertEquals(job.getSalary(),salary);
			Assert.assertEquals(job.getDepartment().getBoId(),deptBoId);
			return 1;
		}
		return 0;
	}
	
	@Test
	public void newParseJobTest() throws Exception {
		jobList = dataLoader.loadJob();
		Assert.assertTrue(jobList != null && jobList.size() == 6);
		int successCount = 0;
		for (Job job : jobList) {
			successCount+= assertJob(job,"JOB001","CEO",800000,"DEPT001");
			successCount+= assertJob(job,"JOB002","Senior Software Engineer",400000,"DEPT002");
			successCount+= assertJob(job,"JOB003","Software Engineer",300000,"DEPT002");
			successCount+= assertJob(job,"JOB004","General Manager",400000,"DEPT002");
			successCount+= assertJob(job,"JOB005","Technical lead",300000,"DEPT002");
			successCount+= assertJob(job,"JOB006","InternShip",40000,"DEPT003");
			//Assert.assertTrue(successCount==1);
			//successCount=0;
		}
		Assert.assertTrue(successCount==6);
	}
	
	@Ignore
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
