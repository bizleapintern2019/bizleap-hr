package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;

@Service
public class DataManagerImpl implements DataManager {
	
	@Autowired
	private CompanySaver companySaver;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private DataLoader dataLoader;
	
//	@Autowired
//	private SaverJDBC saver;
	
	private List<Employee> employeesList;
	private List<Company> companiesList;
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	public List<Employee> getEmployeesList() {
		if(employeesList == null)
			employeesList = new ArrayList<Employee>(); 
		return employeesList;
	}

	public List<Company> getCompanyList() {
		if(companiesList == null)
			companiesList = new ArrayList<Company>();
		return companiesList;
	}

	private void reportError(Map<Integer, Error> map) {
		if (map != null && !map.isEmpty()) {
			logger.info("\t\t\t\t\t\tERROR\n" + map);
			System.exit(0);
		}
	}

	private void loadData() throws Exception {
		employeesList = dataLoader.loadEmployee();
		companiesList = dataLoader.loadCompany();
		reportError(dataLoader.getErrorMap());
	}

	private void associateData() {
		associationMapper.setUpAssociations();
		reportError(associationMapper.getErrorMap());
	}

//	private void saveData() {
//		saver.saveCompanies(companyList);
//		saver.saveEmployees(employeesList);
//	}
	
	private void saveData() {
		companySaver.setCompanyList(companiesList);
		try {
			companySaver.savePass1();
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() throws Exception {
		loadData();
		associateData();
		saveData();
	}
}