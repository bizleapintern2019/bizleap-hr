package com.bizleap.hr.service.impl;

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
	CompanyServiceJDBC companyService;
	
	@Autowired
	EmployeeServiceJDBC employeeService;
	
	public void saveCompany(Company company){
		companyService.saveCompany(company);
	}
	
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
}
