package com.bizleap.hr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.JobService;

@Controller
@RequestMapping(value = "/job")
public class JobController {

	private static Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllJobs(HttpServletRequest request, Model model) {

		List<Job> jobList = new ArrayList<Job>();

		try {
			jobList = jobService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		model.addAttribute("jobList", jobList);
		return "content";
	}
}