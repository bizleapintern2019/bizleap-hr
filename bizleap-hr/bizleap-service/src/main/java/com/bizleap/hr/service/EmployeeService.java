package com.bizleap.hr.service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeService {
    void saveEmployee(Employee employee)throws ServiceUnavailableException;
}
