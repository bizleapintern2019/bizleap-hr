package com.bizleap.hr.loader.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class AssociationMapperImpl implements AssociationMapper {

	@Autowired
	private DataManager dataManager;

	@Autowired
	private ErrorHandler errorHandler;

	private int index = 0;	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private void addEmployeesToCompany(Company company) {

		for(Employee employee : dataManager.getEmployeeList()) {

			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {

		for(Company company : dataManager.getCompanyList()) {
			addEmployeesToCompany(company);
		}	
	}

	private void addCompanyToEmployee(Employee employee) {

		for(Company company : dataManager.getCompanyList()) {

			if(company.isEqual(employee.getWorkForBoId())) {
				employee.setWorkFor(company);
				return; 
			}
		}
		errorHandler.handleLinkageError(++index, "Company in employee cannot be linked.", employee);
	}

	private void setUpEmployeeAssociations() {

		for(Employee employee : dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}