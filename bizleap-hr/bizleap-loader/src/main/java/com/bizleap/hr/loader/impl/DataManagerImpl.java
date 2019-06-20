package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.LoadingError;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

@Service
public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	DataLoaderImpl dataLoader;
	
	@Autowired
	ErrorHandler errorHandler;
	
	@Autowired
	AssociationMapper associationMapper;
	
	@Autowired
	Saver saver;
	
	private List<Employee> employeeList;
	private List<Company> companyList;
	Map<Integer, LoadingError> errorMap = new HashMap<Integer, LoadingError>();

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

	public Map<Integer, LoadingError> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, LoadingError> errorMap) {
		this.errorMap = errorMap;
	}

	public void loadData() {
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		}catch(Exception e) {
			logger.error(e);
		}
	}

	public void saveData() {
		for(Company company: getCompanyList()) {
			if(company != null) {
				saver.saveCompany(company);
			}
		}
		for(Employee employee : getEmployeeList()) {
			if(employee != null) {
				saver.saveEmployee(employee);
			}
		}
	}

	public void associateData() {
		associationMapper.setUpAssociations();
	}

	public void load() {
		loadData();
		associateData();
		if(errorHandler.hasError()) {
			logger.info("Error occurs. Data cannot be saved in relational database.");
			logger.error(errorHandler.getErrorMap());
			System.exit(0);
		}
		else {
			saveData();
			logger.info("Datas are saved in relational database."); 
		}
	}
}