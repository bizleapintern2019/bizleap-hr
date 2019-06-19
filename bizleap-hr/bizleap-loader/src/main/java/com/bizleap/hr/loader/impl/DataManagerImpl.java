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
	
	private Logger logger = Logger.getLogger(DataManagerImpl.class);

	DataLoaderImpl dataLoader;
	ErrorHandler errorHandler = new ErrorHandlerImpl();

	List<Employee> employeeList;
	List<Company> companyList;

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

	public void loadData() throws Exception {

		dataLoader = new DataLoaderImpl(errorHandler);
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		
		if(errorHandler.hasError()) {
			logger.info("\n" + errorHandler.getErrorMap());
			System.exit(0);
		}
		
	}

	public void associateData() {
		
		AssociationMapper associationMapper = new AssociationMapperImpl(this, errorHandler);
		associationMapper.setUpAssociations();
		
		if(errorHandler.hasError()) {
			logger.info("\n" + errorHandler.getErrorMap());
			System.exit(0);
		}
	}

	public void saveData() {
		
		Saver saver = new SaverImpl();
		saver.saveCompanies(companyList);
		saver.saveEmployees(employeeList);
	}

	public void load() throws Exception{

		loadData();
		associateData();
		saveData();
	}
}