package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<Employee, String> implements EmployeeDao {
    protected EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public void save(Employee employee) throws ServiceUnavailableException {
        saveOrUpdate(employee);
    }
}