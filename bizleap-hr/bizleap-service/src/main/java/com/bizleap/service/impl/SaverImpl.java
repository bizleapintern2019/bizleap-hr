package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

public class SaverImpl implements Saver {
	CompanyService companyservice = new CompanyServiceImpl();
	EmployeeService employeeservice = new EmployeeServiceImpl();
	
	public void companySave(Company company) {
		companyservice.saveCompany(company);
	}
	public void employeesave(Employee employee) {
		employeeservice.saveEmployee(employee);
	}
}
