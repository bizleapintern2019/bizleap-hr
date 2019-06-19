package com.bizleap.hr.loader.impl;

import java.util.List;
import org.apache.log4j.Logger;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

public class DataManagerImpl implements DataManager {
	DataLoaderImpl dataLoader;
	private Logger logger =Logger.getLogger(DataManagerImpl.class);
	List<Employee> employeeList;
	List<Company> companyList;
	ErrorHandler errorHandler;
	
	public DataManagerImpl() {
		errorHandler =new ErrorHandlerImpl();
	}

	public DataLoaderImpl getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoaderImpl dataLoader) {
		this.dataLoader = dataLoader;
	}

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
		} catch (Exception ex) {
			logger.info(ex);
		}
	}
	
	public void save() {
		Saver save =new SaverImpl();
		for(Company company: companyList) {
			logger.info(company);
			if(company!=null) {
				save.saveCompany(company);
				logger.info(company);
			}
		}
		for(Employee employee: employeeList) {
			logger.info(employee);
			if(employee!=null) {
				save.saveEmployee(employee);
				logger.info(employee);
			}
		}
	}
	
	public void associateData() {
		if(!errorHandler.hasError()) {
			AssociationMapper associationMapper = new AssociationMapperImpl(this,errorHandler);
			associationMapper.setUpAssociations();
			return;
		}
		logger.info("Error occurs. Association cannot be run.");
		logger.error(errorHandler.getErrorMap());
		System.exit(0);
	}
	public void load() {
		loadData();
		associateData();
		save();
	}
}
