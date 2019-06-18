package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;

public class AssociationMapperImpl implements AssociationMapper{
	private DataManager dataManager;
	private Map<Integer, ErrorCollection> errorHashMap;
	int lineNumber=0;
	
	public AssociationMapperImpl() {
	
	}
	
	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager=dataManager;
	}
	

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	
//	public Map<Integer, ErrorCollection> getErrorHashMap() {
//		return this.errorHashMap;
//	}
//
//	public void setErrorHashMap(HashMap<Integer, ErrorCollection> errorHashMap) {
//		this.errorHashMap = errorHashMap;
//	}

	private void addEmployeesToCompany(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(employee.getWorkFor().isEqual(company.getBoId())) {
				company.addEmployee(employee);
			}
		}
	}
	
	private void addEmployeesToCompany2(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}
	
	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()){
			addEmployeesToCompany(company);
			//System.out.println(company);
		}	
	}
	
	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.sameBoId(employee.getWorkFor())){
				employee.setWorkFor(company);
				return;
			}
		}
		//System.out.println("Employee is"+employee);
		//System.out.println("In HandleLinedError");
		System.out.println("Sending Times");
		lineNumber=dataManager.getDataLoader().getIndex();
		dataManager.getDataLoader().handleLinkedError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataManager.getDataLoader().setIndex(lineNumber);
	}
	
	/*private void addCompanyToEmployee2(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.isEqual(employee.getWorkForBoId())){
				employee.setWorkFor(company);
			}
		}
	}*/
	
	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
			//System.out.println(employee);
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
