package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.internhr.service.resource.DepartmentServiceResource;
import com.bizleap.service.DepartmentService;

@RestController
@RequestMapping(value= {"/departments"})
public class DepartmentServiceResourceImpl implements DepartmentServiceResource {
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Department> getAllDepartments(HttpServletRequest request) throws ServiceUnavailableException {
		return departmentService.getAllDepartment();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createDepartment(HttpServletRequest request,@RequestBody Department department) {
			try {
				departmentService.saveDepartment(department);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody List<Department> findByDepartmentBoId(
			HttpServletRequest request,
			@PathVariable String boId) throws ServiceUnavailableException {
		return departmentService.findByBoId(boId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public @ResponseBody List<Department> findByDepartmentName(HttpServletRequest request,
			@RequestParam(value = "name") String name) throws ServiceUnavailableException {
		return departmentService.findByName(name);
	}
}
