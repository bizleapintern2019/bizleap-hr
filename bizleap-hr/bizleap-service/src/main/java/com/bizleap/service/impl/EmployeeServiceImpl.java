package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.EmployeeDao;
import com.bizleap.service.EmployeeService;

//@Author: Nyan Lin Htet
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void saveEmployee(Employee employee) throws IOException, ServiceUnavailableException {
		employeeDao.save(employee);
	}
}