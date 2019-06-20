package com.bizleap.hr.loader.impl;

import javax.persistence.AttributeOverride;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);
	
	@Autowired
	DataManager dataManager;
	
	@Autowired
	DataLoader dataLoader;
	
	@Autowired
	ErrorHandler errorHandler;
	
	int lineNumber = 0;
	
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	private void addEmployeesToCompany(Company company) {
		for(Employee employee : dataManager.getEmployeeList()) {
			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
		}		
	}

	private void addCompanyToEmployee(Employee employee) {
		for(Company company : dataManager.getCompanyList()) {
			if(company.sameBoId(employee.getWorkFor())) {
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber = dataLoader.getIndex();
		errorHandler.handleLinkedError(lineNumber, "Company and employee cannot be linked.", employee);
		lineNumber++;
		dataLoader.setIndex(lineNumber);
	}

	private void setUpEmployeeAssociations() {
		for(Employee employee : dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}