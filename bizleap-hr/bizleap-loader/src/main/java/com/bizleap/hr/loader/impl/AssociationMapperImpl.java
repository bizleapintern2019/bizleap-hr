package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;

@Service
public class AssociationMapperImpl implements AssociationMapper {

	private Logger logger=Logger.getLogger(AssociationMapperImpl.class);
	
	@Autowired
	private DataManager dataManager;
	
	private Map<Integer, Error> errorMap;
	private List<Integer> lineNumbers =new ArrayList<Integer>();
	private int i=0;
	
	
	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
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
			if(company.boIdIsEqual(employee.getWorkFor().getBoId())) {
				employee.setWorkFor(company);
				i++;
				logger.info(employee);
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
		if (errorMap == null)
			errorMap = new HashMap<Integer, Error>();
		errorMap.put(lineNumber,new Error(lineNumber, source, message));
	}
}
