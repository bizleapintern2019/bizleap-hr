package com.bizleap.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.EmployeeService;
import com.bizleap.hr.service.Saver;

@Service
public class SaverImpl implements Saver{
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	public void saveCompanies(List<Company> companies) {
		
		for(Company company : companies) 
			companyService.saveCompany(company);
	}

	public void saveEmployees(List<Employee> employees) {

		for(Employee employee : employees)
			employeeService.saveEmployee(employee);
	}
}
