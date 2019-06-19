package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.LoadingError;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	DataLoaderImpl dataLoader;
	private List<Employee> employeeList;
	private List<Company> companyList;
	Map<Integer, LoadingError> errorMap = new HashMap<Integer, LoadingError>();
	ErrorHandler errorHandler;
	
	public DataManagerImpl() {
		errorHandler = new ErrorHandlerImpl();
	}
	
	public DataLoaderImpl getDataLoader() {
		return dataLoader;
	}
	
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

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public void loadData() {
		try {
			dataLoader = new DataLoaderImpl(errorHandler);
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		}catch(Exception e) {
			logger.error(e);
		}
	}
	
	public void save() {
		Saver saver = new SaverImpl();
		for(Company company: companyList) {
			if(company != null) {
				saver.saveCompany(company);
			}
		}
		for(Employee employee : employeeList) {
			if(employee != null) {
				saver.saveEmployee(employee);
			}
		}
	}
	
	public void associateData() {
		if(!errorHandler.hasError()) {
			AssociationMapper associationMapper = new AssociationMapperImpl(this,errorHandler);
			associationMapper.setUpAssociations();
			return;
		}
		logger.info("Error occurs. Data cannot be associated.");
		//System.exit(0);
	}
	
	public void load() {
		loadData();
		associateData();
		if(!errorHandler.hasError())
			save();
	}
}