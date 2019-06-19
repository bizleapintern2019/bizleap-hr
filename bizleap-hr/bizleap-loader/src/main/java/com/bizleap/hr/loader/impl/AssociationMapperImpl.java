package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

public class AssociationMapperImpl implements AssociationMapper {
	
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);

	private DataManager dataManager;
	ErrorHandler errorHandler;
	private Map<Integer, Error> errorMap;
	int index = 0;
	
	public AssociationMapperImpl() {

	}

	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	public AssociationMapperImpl(DataManager dataManager, ErrorHandler errorHandler) {
		this.dataManager = dataManager;
		this.errorHandler = errorHandler;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public Map<Integer, Error> getErrorMap() {
		return this.errorMap;
	}
	
	public void setErrorMap(HashMap<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	private void addEmployeesToCompany(Company company) {

		for(Employee employee : dataManager.getEmployeeList()){

			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company : dataManager.getCompanyList()){

			addEmployeesToCompany(company);
			logger.info(company);
		}	
	}

	private void addCompanyToEmployee(Employee employee) {
		

		for(Company company : dataManager.getCompanyList()){

			if(company.isEqual(employee.getWorkForBoId())){
				employee.setWorkFor(company);
				return; 
			}
		}
		errorHandler.handleLinkedError(++index, "Company in employee cannot be linked.", employee);
	}

	private void setUpEmployeeAssociations() {

		for(Employee employee : dataManager.getEmployeeList()) {

			addCompanyToEmployee(employee);
			logger.info(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}