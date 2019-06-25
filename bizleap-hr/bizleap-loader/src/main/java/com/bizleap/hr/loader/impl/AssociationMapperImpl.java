package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;

@Service
public class AssociationMapperImpl implements AssociationMapper{

	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	private int lineNumber=0;
	
	private void addEmployeesToCompany(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(employee.getWorkFor().isEqual(company.getBoId())) {
				company.addEmployee(employee);
			}
		}
	}
	
	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()){
			addEmployeesToCompany(company);
		}	
	}
	
	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.sameBoId(employee.getWorkFor())){
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber=dataLoader.getIndex();
		errorHandler.handleLinkedError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataLoader.setIndex(lineNumber);
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);			
		}
	}
	
	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}
