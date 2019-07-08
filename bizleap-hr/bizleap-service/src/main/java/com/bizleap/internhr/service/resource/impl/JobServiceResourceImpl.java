package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.internhr.service.resource.JobServiceResource;
import com.bizleap.service.JobService;
import com.bizleap.service.LocationService;

@RestController
@RequestMapping(value= {"/locations"})
public class JobServiceResourceImpl implements JobServiceResource{
	
	private Logger logger = Logger.getLogger(JobServiceResourceImpl.class);
	@Autowired
	JobService jobService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Job> getAllJob(HttpServletRequest request) throws ServiceUnavailableException {
		logger.info("Size "+jobService.getAll().size());
		return jobService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createJob(HttpServletRequest request,@RequestBody Job job) {
			try {
				jobService.saveJob(job);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody List<Job> findByJobBoId(
			HttpServletRequest request,
			@PathVariable("boId") String boId) throws ServiceUnavailableException {
		return jobService.findByBoId(boId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{salary}")
	public @ResponseBody List<Job> findByJobSalary(
			HttpServletRequest request,
			@PathVariable("salary") int salary) throws ServiceUnavailableException {
		return jobService.findBySalary(salary);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{title}")
	public @ResponseBody List<Job> findByJobTitle(
			HttpServletRequest request,
			@PathVariable("title") String title) throws ServiceUnavailableException {
		return jobService.findByBoId(title);
	}
	
}
