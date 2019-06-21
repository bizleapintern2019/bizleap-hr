package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
//import com.bizleap.hr.service.SaverJDBC;

@Service
public class DataManagerImpl implements DataManager {

	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private CompanySaver companySaver;
	
//	@Autowired
//	private SaverJDBC saverJDBC;

	private List<Employee> employeeList;
	private List<Company> companyList;

	public List<Employee> getEmployeeList() {
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

	/*private void saveData() {
		
		saverJDBC.saveCompanies(companyList);
		saverJDBC.saveEmployees(employeeList);
	}*/
	
	public void load() {

		try {
			loadData();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		associateData();
		
		companySaver.setCompanyList(companyList);
		
		try {
			companySaver.savePass1();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
//		saveData();
	}
}