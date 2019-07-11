package com.bizleap.hr.controller;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.utils.Parser;
import com.bizleap.service.DepartmentService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.JobService;
import com.bizleap.service.LocationService;
import com.bizleap.service.PositionService;

@Controller
public class DetailController {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired 
	private PositionService positionService;
	
	private static Logger logger = Logger.getLogger(DepartmentController.class);
	@RequestMapping(value = "detail/{entityType}", method = RequestMethod.GET)
	public String detail(@RequestParam("input") String input, @PathVariable("entityType") String entityType, Model model) throws ServiceUnavailableException {
		JSONObject json = Parser.parseJSon(input);
		
		if(json == null) {
			model.addAttribute("status", "Error");
			return "detail";
		}
		
		String boId = (String) json.get("boId");
		
		switch (entityType) {
		case "LOCATION":
			Location location = locationService.findByBoId(boId).get(0);
			model.addAttribute("location", location);
			break;
			
		case "DEPARTMENT":
			Department department = departmentService.findByBoId(boId).get(0);
			model.addAttribute("department", department);
			break;
			
		case "JOB":
			Job job = jobService.findByBoId(boId);
			model.addAttribute("job", job);
			break;
			
		case "EMPLOYEE":
			Employee employee = employeeService.findByBoId(boId);
			logger.info("Position>>>>"+employee.getPosition());
			Position position = positionService.findPositionByEmployeeBoId(employee.getBoId());
			Job employeeJob = jobService.findJobByPositionBoId(position.getBoId());
			position.setJob(employeeJob);
			employee.setPosition(position);
			model.addAttribute("employee", employee);
			break;

		default:
			break;
		}
		return "detail";
	}

}
