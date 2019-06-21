package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.service.SaverJDBC;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

@Service
public class DataManagerImpl implements DataManager {

	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private DataLoader dataLoader;
	
//	@Autowired
//	private SaverJDBC saver;
	
	@Autowired
	CompanySaver companySaver;
	
	private List<Employee> employeeList = new ArrayList<Employee>();
	private List<Company> companyList = new ArrayList<Company>();
	

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	private void reportError(Map<Integer, Error> map) {
		if (map != null && !map.isEmpty()) {
			logger.info("\t\t\t Error \n" + map);
			System.exit(0);
		}
	}

	private void loadData() throws Exception {
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		reportError(dataLoader.getErrorMap());
	}

	private void associateData() {
		associationMapper.setUpAssociations();
		reportError(associationMapper.getErrorMap());
	}

//	private void saveData() {
//		saver.saveCompanies(companyList);
//		saver.saveEmployees(employeeList);
//	}
	private void saveData() {
		companySaver.setCompanyList(companyList);
		try {
			companySaver.savePass1();
		} catch (ServiceUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load() throws Exception{
		loadData();
		associateData();
		saveData();
	}
}