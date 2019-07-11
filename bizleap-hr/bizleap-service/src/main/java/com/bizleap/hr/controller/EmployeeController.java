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

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.EmployeeService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllEmployees(HttpServletRequest request, Model model) {

		List<Employee> employeeList = new ArrayList<Employee>();

		try {
			employeeList = employeeService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		model.addAttribute("employeeList", employeeList);
		return "content";
	}
}