package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	
	//private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private DataManager dataManager;
	
	private Map<Integer, Error> errorMap;
	private List<Integer> lineNumbers = new ArrayList<Integer>();
	private int i = 0;
	private int index = 0;

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	private void addEmployeesToCompany(Company company) {
		for (Employee employee : dataManager.getEmployeesList()) {
			if (company.sameBoId(employee.getWorkFor()))
				company.addEmployee(employee);
		}
	}

	private void setUpCompanyAssociation() {
		for (Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
			//logger.info(company);
		}
	}

	private void addCompanyToEmployee(Employee employee) {
		for (Company company : dataManager.getCompanyList()) {
			if (company.boIdIsEqual(employee.getWorkFor().getBoId())) {
				employee.setWorkFor(company);
				i++;
				return;
			}
		}
		handleLinkageError(lineNumbers.get(i), "Company in employee cannot be linked.", employee);
		i++;
	}

	private void setUpEmployeeAssociation() {
		for (Employee employee : dataManager.getEmployeesList()) {
			addCompanyToEmployee(employee);
			//logger.info(employee);
		}
	}

	public void setUpAssociations() {
		lineNumbers = Employee.getLineNumberList();
		setUpCompanyAssociation();
		setUpEmployeeAssociation();
	}

	public void handleLinkageError(int lineNumber, String message, Object source) {
		if (errorMap == null)
			errorMap = new HashMap<Integer, Error>();

		errorMap.put(index++, new Error(lineNumber, source, message));
	}
}