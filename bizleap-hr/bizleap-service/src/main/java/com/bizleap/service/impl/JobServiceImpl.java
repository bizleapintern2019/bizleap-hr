package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.JobDao;
import com.bizleap.service.JobService;

//@Author: Nyan Lin Htet
@Service
//@Transactional(readOnly = true)
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobDao jobDao;
	
	public void saveJob(Job job) throws IOException, ServiceUnavailableException {
		jobDao.save(job);
	}

	public List<Job> getAll() throws ServiceUnavailableException {
		
		List<Job> jobList = jobDao.getAll("From Job job");
		return jobList;
	}

	public List<Job> findByBoId(String boId) throws ServiceUnavailableException {
		
		String query = "select job from Job job where job.boId=:dataInput";
		List<Job> jobList = jobDao.findByString(query, boId);
		return jobList;
	}
}