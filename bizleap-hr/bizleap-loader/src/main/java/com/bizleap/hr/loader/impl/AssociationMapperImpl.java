package com.bizleap.hr.loader.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);
	
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	ErrorHandler errorHandler;
	
	int index = 0;	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	private void addEmployeesToCompany(Company company) {

		for(Employee employee : dataManager.getEmployeeList()){

			if(company.sameBoId(employee.getWorkFor())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company : dataManager.getCompanyList()){

			addEmployeesToCompany(company);
			logger.info(company);
		}	
	}

	private void addCompanyToEmployee(Employee employee) {
		
		for(Company company : dataManager.getCompanyList()){

			if(company.isEqual(employee.getWorkForBoId())){
				employee.setWorkFor(company);
				return; 
			}
		}
		errorHandler.handleLinkedError(++index, "Company in employee cannot be linked.", employee);
	}

	private void setUpEmployeeAssociations() {

		for(Employee employee : dataManager.getEmployeeList()) {

			addCompanyToEmployee(employee);
			logger.info(employee);
		}
	}

	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}
}