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
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.service.SaverJDBC;

@Service
public class DataManagerImpl implements DataManager {

	private Logger logger = Logger.getLogger(DataManagerImpl.class);

	@Autowired
	private AssociationMapper associationMapper;

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private ErrorCollector errorCollector;

	//@Autowired
	//private SaverJDBC saver;
	@Autowired
	private CompanySaver companySaver;

	private List<Employee> employeeList;
	private List<Company> companyList;
	Map<Integer, Error> errorMap = new HashMap<Integer, Error>();

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

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	public void loadData() {
	
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportError();
	}

	/*public void saveData() {
		for (Company company : companyList) {
			if (company != null) {
				saver.saveCompany(company);
			}
		}
		for (Employee employee : employeeList) {
			if (employee != null) {
				saver.saveEmployee(employee);
			}
		}
	}*/

	public void associateData() {	
		associationMapper.setUpAssociations();
		reportError();
		// System.exit(0);
	}

	private void reportError() {
		if(errorCollector.hasError()) {
			logger.error("Error :"+errorCollector.getErrorHashMap());
			System.exit(0);
		}
	}
	
	public void load() {
		loadData();
		associateData();
		//logger.info("Company List is"+companyList);
		companySaver.setCompanyList(companyList);
		logger.info("Company Saver is"+companySaver.toString());
		try {
			companySaver.savePass1();
			} catch (ServiceUnavailableException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			}catch(Exception e) {
				logger.error(e);
			}
		//saveData();
	}

	public void saveData() {
		// TODO Auto-generated method stub
		
	}
}
