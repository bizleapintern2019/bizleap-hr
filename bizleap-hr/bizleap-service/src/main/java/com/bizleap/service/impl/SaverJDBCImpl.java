package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

public class SaverImpl implements Saver{

	CompanyService companyService = new CompanyServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	
	public SaverImpl() {
	}

	public void saveCompany(Company company){
		companyService.saveCompany(company);
	}
	
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
}
