package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;

public class AssociationMapperImpl implements AssociationMapper {

	private Logger logger=Logger.getLogger(AssociationMapperImpl.class);
	private DataManager dataManager;
	private Map<Integer, Error> errorMap;
	private List<Integer> lineNumbers = new ArrayList<Integer>();
	private int i=0;

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
		return errorMap;
	}

	public void setErrorHashMap(HashMap<Integer, Error> errorHashMap) {
		this.errorMap = errorHashMap;

	}

	private void addEmployeesToCompany(Company company) {
		for (Employee employee : dataManager.getEmployeesList()) {
			if (employee.checkEmployee(company.getBoId()))
				company.setEmployeeList(employee);
		}
	}

	private void setUpCompanyAssociation() {
		for (Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
			logger.info(company);
		}
	}

	private void addCompanyToEmployee(Employee employee) {
		for (Company company : dataManager.getCompanyList()) {
			if (company.checkCompany(employee.getWorkFor().getBoId())) {
				employee.setWorkFor(company);
				i++;
				logger.info(employee);
				return;
			}
		}
		handleLinkedError(lineNumbers.get(i),"Company in employee cannot be linked.", employee);
		i++;
	}

	private void setUpEmployeeAssociation() {
		for (Employee employee : dataManager.getEmployeesList()) {
			addCompanyToEmployee(employee);
		}
	}

	public void setUpAssociations() {
		lineNumbers = Employee.getLineNumberList();
		setUpCompanyAssociation();
		setUpEmployeeAssociation();
	}

	public void handleLinkedError(int lineNumber, String message, Object source) {
		if (!isError())
			errorMap = new HashMap<Integer, Error>();
		errorMap.put(lineNumber,new Error(lineNumber, source, message));
	}
	
	public boolean isError(){
		return errorMap!=null && !errorMap.isEmpty();
	}
}
