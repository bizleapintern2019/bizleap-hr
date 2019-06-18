package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

public class DataManagerImpl implements DataManager {
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	private List<Employee> employeesList = new ArrayList<Employee>();
	private List<Company> companyList = new ArrayList<Company>();

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void loadData() throws Exception {
		DataLoader dataLoader = new DataLoaderImpl();
		employeesList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		if (dataLoader.hasError()) {
			logger.info("\t\t\t\t\t\tFileERROR\n" + dataLoader.getFileErrorMap());
			System.exit(0);
		}
	}

	public void associateData() {
		AssociationMapper associationMapper = new AssociationMapperImpl(this);
		associationMapper.setUpAssociations();
		if (associationMapper.hasError()) {
			logger.info("\t\t\t\t\t\tLinkedERROR\n" + associationMapper.getAssociateErrorMap());
			System.exit(0);	
		}
	}

	public void save() {
		Saver saver = new SaverImpl();
		saver.saveCompanies(companyList);
		saver.saveEmployees(employeesList);
	}

	public void load() throws Exception {
		loadData();
		associateData();
		save();
	}
}