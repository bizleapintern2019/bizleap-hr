package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeService {
	
	void saveEmployee(Employee employee) throws IOException, ServiceUnavailableException;
	List<Employee> getAll() throws ServiceUnavailableException;
	List<Employee> findByBoId(String boId) throws ServiceUnavailableException;
}