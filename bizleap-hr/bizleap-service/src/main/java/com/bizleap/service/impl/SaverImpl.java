package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

public class SaverImpl implements Saver {

	@Override
	public void saveCompany(Company company) {
		CompanyService companyService = new CompanyServiceImpl();
		companyService.saveCompany(company);
	}

	@Override
	public void saveEmployee(Employee employee) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		employeeService.saveEmployee(employee);
	}
}