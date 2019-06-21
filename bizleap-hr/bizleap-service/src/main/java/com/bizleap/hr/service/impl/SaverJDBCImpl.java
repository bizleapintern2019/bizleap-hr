package com.bizleap.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.CompanyServiceJDBC;
import com.bizleap.hr.service.EmployeeServiceJDBC;
import com.bizleap.hr.service.SaverJDBC;

@Service
public class SaverJDBCImpl implements SaverJDBC{
	
	@Autowired
	private CompanyServiceJDBC companyServiceJDBC;
	
	@Autowired
	private EmployeeServiceJDBC employeeServiceJDBC;
	
	public void saveCompanies(List<Company> companies) {
		
		for(Company company : companies) 
			companyServiceJDBC.saveCompany(company);
	}

	public void saveEmployees(List<Employee> employees) {

		for(Employee employee : employees)
			employeeServiceJDBC.saveEmployee(employee);
	}
}
