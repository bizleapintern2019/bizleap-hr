package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Job;

public class JobTest {

	@Test
	public void parseJobTest() {
		Job.parseJob("JOB001;CEO;800000;DEPT001");
	}
}
