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
	
	/*@Autowired
	private SaverJDBC saver;*/
	@Autowired
	private CompanySaver companySaver;
	
	private List<Employee> employeesList = new ArrayList<Employee>();
	private List<Company> companyList = new ArrayList<Company>();

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}
	
	public void reportError(Map<Integer,Error> map) {
		if(map!=null && !map.isEmpty()){
			logger.info("\t\t\t\t\t\tFileERRORr\n"+map);
			//System.exit(0);
		}
	}

	public void loadData() throws Exception {
		employeesList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		reportError(dataLoader.getErrorMap());
	}

	/*public void saveData() {
		companySaver.saveCompanies(companyList);
		saver.saveEmployees(employeesList);
	}*/

	public void associateData() {
		associationMapper.setUpAssociations();
		reportError(associationMapper.getErrorMap());
	}

	public void load() {
		try{
		loadData();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		associateData();
		companySaver.setCompanyList(companyList);
		
		try{
		companySaver.savePass1();
		}catch(ServiceUnavailableException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		//saveData();
	}
}


