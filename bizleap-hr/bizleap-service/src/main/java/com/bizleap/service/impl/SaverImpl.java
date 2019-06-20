package com.bizleap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

@Service
public class SaverImpl implements Saver {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public void saveCompany(Company company) {
		companyService.saveCompany(company);
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
	}
}