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
import com.bizleap.commons.domain.entity.LoadingError;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.service.SaverJDBC;

@Service
public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private DataLoaderImpl dataLoader;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private CompanySaver companySaver;
	
//	@Autowired
//	private SaverJDBC saver;

	private List<Employee> employeeList;
	private List<Company> companyList;

	public void setDataLoader(DataLoaderImpl dataLoader) {
		this.dataLoader = dataLoader;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	private void reportError() {
		if(errorHandler.hasError()) {
			logger.info("Errors. Data cannot be loaded and associated.");
			logger.error(errorHandler.getErrorMap());
			System.exit(0);
		}
	}
	
	public void loadData() {
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		} catch(Exception e) {
			logger.error(e);
		}
		reportError();
	}

	public void associateData() {
		associationMapper.setUpAssociations();
		reportError();
	}

/*	For JDBC,
	public void saveData() {
		for(Company company: getCompanyList()) {
			if(company != null) 
				saver.saveCompany(company);
		}
		
		for(Employee employee : getEmployeeList()) {
			if(employee != null)
				saver.saveEmployee(employee);
		}
	}*/

	public void load() {
		loadData();
		associateData();
		companySaver.setCompanyList(companyList);
		try {
			companySaver.savePass1();
		} catch (ServiceUnavailableException | IOException e) {
			logger.error(e);
		}
//		saveData();
	}
}