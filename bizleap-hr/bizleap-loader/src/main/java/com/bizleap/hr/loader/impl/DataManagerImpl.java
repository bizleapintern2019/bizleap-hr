package com.bizleap.hr.loader.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.service.Saver;

@Service
public class DataManagerImpl implements DataManager {

	private Logger logger = Logger.getLogger(ErrorHandlerImpl.class);
	
	@Autowired
	ErrorHandler errorHandler;
	
	@Autowired
	AssociationMapper associationMapper;
	
	@Autowired
	DataLoader dataLoader;
	
	@Autowired
	Saver saver;

	List<Employee> employeeList;
	List<Company> companyList;

	public List<Employee> getEmployeeList(){
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList=employeeList;
	}

	public List<Company> getCompanyList(){
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList=companyList;
	}

	private void reportError(Map<Integer, Error> errorMap) {

		if(errorMap != null && !errorMap.isEmpty()) {
			logger.info("\n" + errorMap);
			System.exit(0);
		}
	}

	private void loadData() throws Exception {
	
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();

		reportError(errorHandler.getErrorMap());		
	}

	private void associateData() {
		
		associationMapper.setUpAssociations();
		reportError(errorHandler.getErrorMap());
	}

	private void saveData() {
		
		saver.saveCompanies(companyList);
		saver.saveEmployees(employeeList);
	}

	public void load() throws Exception{

		loadData();
		associateData();
		saveData();
	}
}