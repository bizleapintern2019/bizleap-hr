package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;

public class AssociationMapperImpl implements AssociationMapper{

	private DataManager dataManager;
	private Map<Integer, Error> errorHashMap;
	
	public AssociationMapperImpl() {

	}

	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager = dataManager;
	}


	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public Map<Integer, Error> getErrorHashMap() {
		return this.errorHashMap;
	}

	public void setErrorHashMap(HashMap<Integer, Error> errorHashMap) {
		this.errorHashMap = errorHashMap;
	}

	private void addEmployeesToCompany(Company company) {

		for(Employee employee:dataManager.getEmployeeList()){

			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()){

			addEmployeesToCompany(company);
			System.out.println(company);
		}	
	}

	private void addCompanyToEmployee(Employee employee) {

		for(Company company:dataManager.getCompanyList()){

			if(company.isEqual(employee.getWorkForBoId())){
				employee.setWorkFor(company);
				return; 
			}
		}
		handleLinkedError("Company in employee cannot be linked.", employee);
	}

	private void setUpEmployeeAssociations() {

		for(Employee employee:dataManager.getEmployeeList()) {

			addCompanyToEmployee(employee);
			System.out.println(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}

	public void handleLinkedError(String message, Object source) {

		DataLoader dataLoader = dataManager.getDataLoader();
		int index = dataLoader.getIndex();
		errorHashMap = dataLoader.getErrorMap();
		Error error = new Error(source,message);

		if(errorHashMap == null) {
			errorHashMap = new HashMap<Integer, Error>();
		}
		errorHashMap.put(index, error);
		dataLoader.setErrorMap(errorHashMap);
		dataLoader.setIndex(index++);
	}
}