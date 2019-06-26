package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeSaver {
	void savePass1() throws ServiceUnavailableException, IOException;
	void setEmployeeList(List<Employee> employeeList);
}