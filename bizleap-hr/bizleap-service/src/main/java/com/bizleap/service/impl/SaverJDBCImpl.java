package com.bizleap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.SaverJDBC;

@Service
public class SaverJDBCImpl implements SaverJDBC {

	@Autowired
	EmployeeServiceJDBC employeeService;

	@Autowired
	CompanyServiceJDBC companyService;

	@Override
	public void saveEmployee(Employee employee) {

		employeeService.saveEmployee(employee);
	}

	@Override
	public void saveCompany(Company company) {

		companyService.saveCompany(company);

	}
}
