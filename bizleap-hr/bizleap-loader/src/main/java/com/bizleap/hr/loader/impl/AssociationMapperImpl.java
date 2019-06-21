package com.bizleap.hr.loader.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);

	@Autowired
	private DataManager dataManager;

	@Autowired
	private ErrorCollector errorCollector;

	@Autowired
	private DataLoader dataLoader;

	private int lineNumber = 0;

	private void addEmployeesToCompany(Company company) {
		for (Employee employee : dataManager.getEmployeeList()) {
			if (company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for (Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
		}
	}

	private void addCompanyToEmployee(Employee employee) {
		for (Company company : dataManager.getCompanyList()) {
			if (company.sameBoId(employee.getWorkFor())) {
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber = dataLoader.getIndex();
		errorCollector.handleLinkedError(lineNumber, "Company and employee cannot be linked.", employee);
		lineNumber++;
		dataLoader.setIndex(lineNumber);
	}

	private void setUpEmployeeAssociations() {
		for (Employee employee : dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}
