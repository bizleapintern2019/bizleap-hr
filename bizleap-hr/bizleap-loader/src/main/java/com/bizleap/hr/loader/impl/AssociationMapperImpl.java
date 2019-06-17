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

	public AssociationMapperImpl(DataManager dataManager) {
		this.dataManager = dataManager;
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
		for (Employee employee : dataManager.getEmployeesList()) {
			if (employee.checkEmployee(company.getBoId()))
				company.setEmployeeList(employee);
		}
	}

	private void setUpCompanyAssociation() {
		for (Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
			System.out.println(company);
		}
	}

	private void addCompanyToEmployee(Employee employee) {
		for (Company company : dataManager.getCompanyList()) {
			if (company.checkCompany(employee.getWorkFor().getBoId())) {
				employee.setWorkFor(company);
				i++;
				return;
			}
		}
		handleLinkedError(lineNumbers.get(i), "Company in employee cannot be linked.", employee);
		i++;
	}

	private void setUpEmployeeAssociation() {
		for (Employee employee : dataManager.getEmployeesList()) {
			addCompanyToEmployee(employee);
			System.out.println(employee);
		}
	}

	public void setUpAssociations() {
		lineNumbers = Employee.getLineNumberList();
		setUpCompanyAssociation();
		setUpEmployeeAssociation();
	}

	public void handleLinkedError(int lineNumber, String message, Object source) {
		if (errorHashMap == null)
			errorHashMap = new HashMap<Integer, Error>();
		errorHashMap.put(lineNumber,new Error(lineNumber, source, message));
	}
}
