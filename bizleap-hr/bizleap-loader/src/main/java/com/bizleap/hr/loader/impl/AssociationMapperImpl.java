package com.bizleap.hr.loader.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;


import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.commons.domain.entity.Error;


public class AssociationMapperImpl implements AssociationMapper {
	private DataManager dataManager;
	private HashMap<Integer,Error> errorMap;
	private ErrorCollector errorCollector;
	int lineNumber =0;
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);

	public AssociationMapperImpl() {

	}

	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	public AssociationMapperImpl(DataManager dataManager,ErrorCollector errorCollector) {
		this.dataManager=dataManager;
		this.errorCollector=errorCollector;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public HashMap<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(HashMap<Integer, Error> errorHashMap) {
		this.errorMap = errorHashMap;
	}

	public void setErrorCollector(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	@Override
	public ErrorCollector getErrorCollector() {
		return errorCollector;
	}

	private void addEmployeesToCompany(Company company) {
		for(Employee employee:dataManager.getEmployeeList()) {
			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
			logger.info(company);
		}
	}

	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()) {
			if(company.sameBoId(employee.getWorkFor())) {
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber=dataManager.getDataLoader().getIndex();
		errorCollector.handleLinkedError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataManager.getDataLoader().setIndex(lineNumber);
	}

	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
			logger.info(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}

