package com.bizleap.hr.service.resource.impl.test;

import org.junit.Test;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.internhr.service.resource.impl.client.JobServiceRestClient;

public class JobServiceResourceImplTest extends ServiceTest {

	JobServiceRestClient jobServiceRestClient = new JobServiceRestClient();

	@Test
	public void testGetAllEmployee() throws ServiceUnavailableException {
		jobServiceRestClient.getAllJobs();
	}

	@Test
	public void testFindByBoId() throws ServiceUnavailableException {
		jobServiceRestClient.findByJobBoId("JOB001");
	}

	@Test
	public void testFindByJobTitle() throws ServiceUnavailableException {
		jobServiceRestClient.findByJobTitle("CEO");
	}

	@Test
	public void testFindBySalary() throws ServiceUnavailableException {
		jobServiceRestClient.findBySalary("800000");
	}

	@Test
	public void testFindByDepartmentBoId() throws ServiceUnavailableException {
		jobServiceRestClient.findByDepartmentId("DEPT001");
	}
}