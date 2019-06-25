package com.bizleap.hr.loader.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.commons.domain.entity.Error;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);

	@Autowired 
	private DataManager dataManager;

	@Autowired
	private ErrorHandler errorHandler;

	@Autowired
	private DataLoader dataLoader;

	private HashMap<Integer,Error> errorMap;
	int lineNumber =0;

	public HashMap<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(HashMap<Integer, Error> errorHashMap) {
		this.errorMap = errorHashMap;
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
			if(company.sameBoId(employee.getWorkFor())) {
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber=dataLoader.getIndex();
		errorHandler.handleLinkageError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataLoader.setIndex(lineNumber);
	}

	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
			logger.info(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}

