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
import com.bizleap.service.Impl.SaverImpl;

public class DataManagerImpl implements DataManager {
	
	private List<Employee> employeeList=new ArrayList<Employee>();
	private List<Company> companyList=new ArrayList<Company>();
	private Logger logger=Logger.getLogger(DataManagerImpl.class);
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public List<Company> getCompanyList() {
		return companyList;
	}
	
	public void loadData() throws Exception {
		DataLoader dataLoader = new DataLoaderImpl();
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		if(dataLoader.getFileError()!=null) {
			logger.info("\t\t\t\t\t\tFileErrorr\n "+dataLoader.getFileError());
			System.exit(0);
		}
	}
	
	public void saveData() {
		Saver saver=new SaverImpl();
		saver.saveCompanies(companyList);
		saver.saveEmployees(employeeList);
	}
	
	public void associateData() {
		AssociationMapper assocaiationMapper=new AssociationMapperImpl(this);
		assocaiationMapper.setUpAssociations();
		if(assocaiationMapper.getErrorHashMap()!=null) {
			logger.info("\t\t\t Linked Error \n"+assocaiationMapper.getErrorHashMap());
			System.exit(0);
		}
	}
	
	public void load() throws Exception {
		loadData();
		associateData();
		//saveData();
	}
}