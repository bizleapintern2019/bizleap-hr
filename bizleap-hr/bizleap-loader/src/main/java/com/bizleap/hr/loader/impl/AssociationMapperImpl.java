package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;

public class AssociationMapperImpl implements AssociationMapper {

	private DataManager dataManager;
	private HashMap<Integer, Error> errorHashMap;
	private List<Integer> lineNumbers = new ArrayList<Integer>();
	private int i=0;
	
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
	
	
	public HashMap<Integer, Error> getErrorHashMap() {
		return errorHashMap;
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
			//System.out.println(company);
		}	
	}
	
	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.boIdIsEqual(employee.getWorkFor().getBoId())){
				employee.setWorkFor(company);
				i++;
				System.out.println(employee);
				return;
				
			}
		}
		handleLinkedError(lineNumbers.get(i),"Company in employee cannot be linked.", employee);
	}
	
//	private void addCompanyToEmployee2(Employee employee) {
//		for(Company company:dataManager.getCompanyList()){
//			if(company.isEqual(employee.getWorkForBoId())){
//				employee.setWorkFor(company);
//			}
//		}
//	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
			
		}
	}
	
	public void setUpAssociations() {
		lineNumbers = Employee.getLineNumberList();
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}

	public void handleLinkedError(int lineNumber,String message, Object source) {
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println("\t\t\t\tERROR");
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(message);
//		System.out.println("Source object : "+source);
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.exit(0);
		if (errorHashMap == null)
			errorHashMap = new HashMap<Integer, Error>();

		Error error = new Error(lineNumber, source, message);
		errorHashMap.put(lineNumber, error);
	}
}
