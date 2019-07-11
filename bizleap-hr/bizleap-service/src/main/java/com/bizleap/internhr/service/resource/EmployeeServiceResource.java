package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeServiceResource {
	
	boolean createEmployee(HttpServletRequest request,@RequestBody Employee employee);
	List<Employee> getAllEmployee(HttpServletRequest request) throws ServiceUnavailableException;
	Employee findByBoId(HttpServletRequest request, String boId) throws ServiceUnavailableException;
	List<Employee> findByFirstName(HttpServletRequest request, String firstName) throws ServiceUnavailableException;
	List<Employee> findByLastName(HttpServletRequest request, String lastName) throws ServiceUnavailableException;
	List<Employee> findByGender(HttpServletRequest request, String gender) throws ServiceUnavailableException;
}