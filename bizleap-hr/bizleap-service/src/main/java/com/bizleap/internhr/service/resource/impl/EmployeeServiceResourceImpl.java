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

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.internhr.service.resource.EmployeeServiceResource;
import com.bizleap.service.EmployeeService;

@RestController
@RequestMapping(value = {"/employee"})
public class EmployeeServiceResourceImpl implements EmployeeServiceResource {
	
	@Autowired 
	EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createEmployee(HttpServletRequest request,@RequestBody Employee employee) {
			try {
				employeeService.saveEmployee(employee);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Employee> getAllEmployee(HttpServletRequest request) throws ServiceUnavailableException {
		return employeeService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody List<Employee> findByBoId(HttpServletRequest request,
		   @PathVariable String boId) throws ServiceUnavailableException {
		return employeeService.findByBoId(boId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/firstName")
	public @ResponseBody List<Employee> findByFirstName(HttpServletRequest request,
		   @RequestParam(value = "firstName") String firstName) throws ServiceUnavailableException {
		return employeeService.findByFirstName(firstName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/lastName")
	public @ResponseBody List<Employee> findByLastName(HttpServletRequest request,
		   @RequestParam(value = "lastName") String lastName) throws ServiceUnavailableException {
		return employeeService.findByLastName(lastName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/gender")
	public @ResponseBody List<Employee> findByGender(HttpServletRequest request,
		   @RequestParam(value = "gender") String gender) throws ServiceUnavailableException {
		return employeeService.findByGender(gender);
	}
}