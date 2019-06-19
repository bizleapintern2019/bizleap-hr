package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

public class AssociationMapperImpl implements AssociationMapper {
	private DataManager dataManager;
	ErrorHandler errorHandler;
	int lineNumber = 0;
	
	public AssociationMapperImpl() {

	}

	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public AssociationMapperImpl(DataManager dataManager, ErrorHandler errorHandler) {
		this.dataManager = dataManager;
		this.errorHandler = errorHandler;
	}
	
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
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
			System.out.println(company);
		}		
	}

	private void addCompanyToEmployee(Employee employee) {
		for(Company company : dataManager.getCompanyList()) {
			if(company.sameBoId(employee.getWorkFor())) {
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber = dataManager.getDataLoader().getIndex();
		errorHandler.handleLinkedError(lineNumber, "Company and employee cannot be linked.", employee);
		lineNumber++;
		dataManager.getDataLoader().setIndex(lineNumber);
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