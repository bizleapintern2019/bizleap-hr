package com.bizleap.hr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import com.bizleap.hr.service.EmployeeService;
import com.bizleap.hr.service.dao.EmployeeDao;

@Service
//@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void saveEmployee(Employee employee) throws ServiceUnavailableException {

    }
}
