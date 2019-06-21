package com.bizleap.hr.loader.impl;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
//import com.bizleap.hr.service.SaverJDBC;

@Service
public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);

	@Autowired
	private DataLoaderImpl dataLoader;

	@Autowired
	private ErrorHandler errorHandler;

	@Autowired
	private AssociationMapper associationMapper;

//	@Autowired
//	private CompanySaver companySaver;
	
//	@Autowired
//	private SaverJDBC saver;

	private Map<Integer,Error> errorMap = new HashMap<Integer,Error>();
	private List<Employee> employeeList;
	private List<Company> companyList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList=employeeList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList=companyList;
	}

	public Map<Integer,Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer,Error> errorMap) {
		this.errorMap = errorMap;
	}

	public void loadData() {
		try {
			companyList = dataLoader.loadCompany();
			employeeList = dataLoader.loadEmployee();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public void associateData() {
		if(!errorHandler.hasError())
			 associationMapper.setUpAssociations();		
	}
	
//	JDBC saver method
//	@Override
//	public void saveData() {
//		if(errorHandler.hasError())
//			return;
//
//		for(Company company: getCompanyList()) {
//			if(company != null) {
//				saver.saveCompany(company);
//			}
//		}
//
//		for(Employee employee: getEmployeeList()) {
//			if(employee != null) {
//				saver.saveEmployee(employee);
//			}
//		}
//	}

	public void load() {
		
		loadData();
		associateData();
//		companySaver.setCompanyList(companyList);
//		try {
//			companySaver.savePass1();
//		} catch (ServiceUnavailableException e) {
//			logger.error(e);
//		} catch (IOException e) {
//			logger.error(e);
//		}
//		saveData();
		if(errorHandler.hasError())
			logger.error("Errors: "+errorHandler.getErrorMap());
	}
}