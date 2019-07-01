package com.bizleap.hr.loader;

import org.junit.Test;
import org.junit.Assert;

import com.bizleap.commons.domain.entity.Job;

public class JobTest {

	@Test
	public void parseJobTest() {
		Job job=Job.parseJob("JOB001;CEO;800000;DEPT001");
		Assert.assertEquals("JOB001", job.getBoId());
		Assert.assertEquals("CEO", job.getJobTitle());
		Assert.assertEquals(800000, job.getSalary());
		Assert.assertEquals("DEPT001", job.getDepartment().getBoId());
	}
}
