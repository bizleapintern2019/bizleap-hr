package com.bizleap.hr.loader.impl;

import java.util.Map;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;

public class AssociationMapperImpl implements AssociationMapper{
	private DataManager dataManager;
	//private Map<Integer, ErrorCollection> errorHashMap;
	ErrorCollector errorCollector;
	int lineNumber=0;
	
	public AssociationMapperImpl() {
	}
	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager=dataManager;
	}
	public AssociationMapperImpl(DataManager dataManager,ErrorCollector errorCollector) {
		this.dataManager=dataManager;
		this.errorCollector=errorCollector;
	}
	
	
	public ErrorCollector getErrorCollector() {
		return errorCollector;
	}

	public void setErrorCollector(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
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
		lineNumber=dataManager.getDataLoader().getIndex();
		errorCollector.handleLinkedError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataManager.getDataLoader().setIndex(lineNumber);
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

	public Map<Integer, ErrorCollection> getErrorHashMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
