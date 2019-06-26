package com.bizleap.service.impl;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.SaverJDBC;

@Ignore
public class SaverJDBCImpl implements SaverJDBC {
	@Autowired
	private CompanyServiceJDBC companyservice;
	@Autowired
	private EmployeeServiceJDBC employeeservice ;
	
	public void saveCompany(Company company) {
		companyservice.saveCompany(company);
	}
	public void saveEmployee(Employee employee) {
		employeeservice.saveEmployee(employee);
	}
}
