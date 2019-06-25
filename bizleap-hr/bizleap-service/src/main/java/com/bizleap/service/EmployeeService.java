package com.bizleap.service;

import java.io.IOException;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeService {
	void saveEmployee(Employee employee) throws IOException, ServiceUnavailableException;
}
