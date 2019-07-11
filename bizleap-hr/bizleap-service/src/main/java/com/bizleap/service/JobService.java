package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobService {
	
	void saveJob(Job job) throws IOException, ServiceUnavailableException;
	List<Job> getAll() throws ServiceUnavailableException;
	Job findByBoId(String boId) throws ServiceUnavailableException;
	List<Job> findByTitle(String title) throws ServiceUnavailableException;
	List<Job> findBySalary(int salary) throws ServiceUnavailableException;
	void hibernateInitializedList(List<Job> jobList);
	Job findJobByPositionBoId(String positionBoId) throws ServiceUnavailableException;
}