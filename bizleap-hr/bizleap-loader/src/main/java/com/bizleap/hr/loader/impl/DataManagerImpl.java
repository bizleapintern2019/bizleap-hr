package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.service.Saver;

@Service
public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private Saver saver;
	
	private List<Employee> employeesList = new ArrayList<Employee>();
	private List<Company> companyList = new ArrayList<Company>();


	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void reportError(Map<Integer, Error> map) {
		if(map != null && !map.isEmpty()) {
			logger.info("\t\t\t\t\t\tERROR\n"+map);
			System.exit(0);
		}
	}

	public void loadData() throws Exception {
		employeesList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		reportError(dataLoader.getErrorMap());
	}

	public void associateData() {
		associationMapper.setUpAssociations();
		reportError(associationMapper.getErrorMap());
	}

	public void saveData() {
		saver.saveCompanies(companyList);
		saver.saveEmployees(employeesList);
	}

	public void load() throws Exception {
		loadData();
		associateData();
		saveData();
	}
}
