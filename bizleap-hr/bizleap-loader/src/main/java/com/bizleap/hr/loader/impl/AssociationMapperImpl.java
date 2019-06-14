package com.bizleap.hr.loader.impl;

import java.util.HashMap;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;


public class AssociationMapperImpl implements AssociationMapper {
	private DataManager dataManager;
	public HashMap<Integer,ErrorCollection> errorHashMap;
	
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

	private void addEmployeesToCompany(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(employee.getWorkFor().isEqual(company.getBoId())) {
				company.addEmployee(employee);
			}
		}
	}
	
	/*private void addEmployeesToCompany2(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}*/
	
	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()){
			addEmployeesToCompany(company);
			System.out.println(company);
		}	
	}
	
	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.sameBoId(employee.getWorkFor())){
				employee.setWorkFor(company);
				return;
			}
		}
		handleLinkedError(122,"Company in employee cannot be linked.", employee);
	}
	
	private void addCompanyToEmployee2(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.isEqual(employee.getWorkForBoId())){
				employee.setWorkFor(company);
			}
		}
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee2(employee);
			System.out.println(employee);
		}
	}
	
	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}

	public void handleLinkedError(int lineNumber,String message, Object source) {
		
		ErrorCollection error=new ErrorCollection(lineNumber,source,message);
		if(errorHashMap == null){
			errorHashMap = new HashMap<Integer, ErrorCollection>();
		}
		errorHashMap.put(122,error);
	}

	public HashMap<Integer, ErrorCollection> getHashMap() {
		
		return this.errorHashMap;
	}

	public void setHasnMap(HashMap<Integer, ErrorCollection> errorHashMap) {
		
		this.errorHashMap=errorHashMap;
	}

}
