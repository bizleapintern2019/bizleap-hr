package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobService {
	
	void saveJob(Job job) throws IOException, ServiceUnavailableException;
	List<Job> getAll() throws ServiceUnavailableException;
	List<Job> findByBoId(String boId) throws ServiceUnavailableException;
}