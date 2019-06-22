package com.bizleap.hr.service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee) throws ServiceUnavailableException;
    List<Employee> getAll() throws ServiceUnavailableException;
    void hibernateInitializeEmployeeList(List<Employee> employeeList) throws ServiceUnavailableException;
}
