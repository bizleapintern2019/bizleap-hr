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

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.DepartmentService;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

	private static Logger logger = Logger.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllDepartments(HttpServletRequest request, Model model) {

		List<Department> departmentList = new ArrayList<Department>();

		try {
			departmentList = departmentService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		model.addAttribute("departmentList", departmentList);
		return "content";
	}
}