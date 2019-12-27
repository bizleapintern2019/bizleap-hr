package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobServiceResource {

    List<Job> getAllJob(HttpServletRequest request) throws ServiceUnavailableException;
	boolean createJob(HttpServletRequest request,Job job);
	Job findByJobBoId(HttpServletRequest request,String boId) throws ServiceUnavailableException;
	List<Job> findByJobSalary( HttpServletRequest request, int salary) throws ServiceUnavailableException;
	List<Job> findByJobTitle(HttpServletRequest request,String title) throws ServiceUnavailableException;
}
