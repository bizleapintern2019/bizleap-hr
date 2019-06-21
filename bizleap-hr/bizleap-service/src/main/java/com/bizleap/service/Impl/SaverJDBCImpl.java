package com.bizleap.service.Impl;

import java.util.List;

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
	private CompanyServiceJDBC companyService;
	
	@Autowired
	private EmployeeServiceJDBC employeeService;
	
	public void saveCompanies(List<Company> companies) {
		for(Company company : companies)
			companyService.saveCompany(company);
	}
	
	public void saveEmployees(List<Employee> employees) {
		for(Employee employee : employees)
			employeeService.saveEmployee(employee);
	}
}
