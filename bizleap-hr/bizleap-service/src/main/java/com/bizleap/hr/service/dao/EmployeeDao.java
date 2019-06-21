package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface EmployeeDao extends AbstractDao<Employee, String> {
    public void save(Employee employee) throws ServiceUnavailableException;
}