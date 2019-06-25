package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.service.SaverJDBC;
import com.bizleap.service.impl.SaverJDBCImpl;

@Service
public class DataManagerImpl implements DataManager {
	
	private Logger logger =Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private ErrorHandler errorHandler;	
	
	@Autowired
	private CompanySaver companySaver;

	private List<Employee> employeeList;
	private List<Company> companyList;
		
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

	public void loadData() {		
		
//		dataLoader.loadCompany();
//		dataLoader.loadEmployee();
			
		try {			
			employeeList = dataLoader.loadEmployee();
			logger.info("Employee List =" + employeeList);
			companyList = dataLoader.loadCompany();
			logger.info("Company List =" + companyList);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	public void saveData() {		
		
//		SaverJDBC saver =new SaverJDBCImpl();
//		for(Company company: getCompanyList()) {
//			logger.info(company);
//			if(company!=null) {
//				saver.saveCompany(company);
//				logger.info(company);
//			}
//		}
//		for(Employee employee: getEmployeeList()) {
//			logger.info(employee);
//			if(employee!=null) {
//				saver.saveEmployee(employee);
//				logger.info(employee);
//			}
//		}
	}
	
	public void associateData() {	
		if(!errorHandler.hasError()) {		
			associationMapper.setUpAssociations();
			return;
		}
		logger.info("Error occurs. Association cannot be run.");
		
	}
	
	public void load() {		
		
		associationMapper.setUpAssociations();
		
		companySaver.setCompanyList(companyList);
		try {
			companySaver.savePass1();
		} catch(ServiceUnavailableException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		loadData();
//		associateData();
//		if(errorHandler.hasError()) {
////			logger.info("Saving in database cannod be run");
//			logger.error("Error Message: "+"Cannot link Employee and Company");
//		} else {
////			saveData();
////			logger.info("Data is Saved into database");
//		}		
	}
}
