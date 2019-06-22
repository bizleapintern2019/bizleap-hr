package com.bizleap.hr.service.impl;


import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import com.bizleap.hr.service.EmployeeService;
import com.bizleap.hr.service.dao.EmployeeDao;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void saveEmployee(Employee employee) throws ServiceUnavailableException {
        employeeDao.save(employee);
    }

    @Override
    public List<Employee> getAll() throws ServiceUnavailableException {
        List<Employee> employeeList = employeeDao.getAll("From Employee employee");
//        hibernateInitializeEmployeeList(employeeList);
        return employeeList;
    }

    @Override
    public void hibernateInitializeEmployeeList(List<Employee> employeeList) throws ServiceUnavailableException {
        for (Employee employee: employeeList) {
            hibernateInitializeEmployee(employee);
        }
    }

    private void hibernateInitializeEmployee(Employee employee) {
        Hibernate.initialize(employee);
    }

}
