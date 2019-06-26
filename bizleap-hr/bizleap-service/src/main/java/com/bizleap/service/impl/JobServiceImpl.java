package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.JobDao;
import com.bizleap.service.JobService;

//@Author: Nyan Lin Htet
@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	
	@Override
	public void saveJob(Job job) throws IOException, ServiceUnavailableException {
		jobDao.save(job);
	}
}