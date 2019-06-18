package com.bizleap.service.impl;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.Saver;

public class SaverImpl implements Saver {

	public void saveCompanies(List<Company> companies) {
		CompanyService companyService=new CompanyServiceImpl();
		for(Company company : companies)
			companyService.saveCompany(company);
	}
	
	public void saveEmployees(List<Employee> employees) {
		EmployeeService employeeService=new EmployeeServiceImpl();
		for(Employee employee : employees)
			employeeService.saveEmployee(employee);
	}
}
