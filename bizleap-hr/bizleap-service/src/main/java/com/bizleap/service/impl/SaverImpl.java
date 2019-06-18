package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

public class SaverImpl implements Saver{

	@Override
	public void saveEmployee(Employee employee) {
		EmployeeService empService = new EmployeeServiceImpl();
		empService.saveEmployee(employee);
	}

	@Override
	public void saveCompany(Company company) {
		CompanyService cmpService= new CompanyServiceImpl();
		cmpService.saveCompany(company);
		
	}
	
}
