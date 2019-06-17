package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;

public class DataManagerImpl implements DataManager {
	
	private List<Employee> employeeList=new ArrayList<Employee>();
	private List<Company> companyList=new ArrayList<Company>();
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public List<Company> getCompanyList(){
		return companyList;
	}
	
	public void loadData() throws Exception {
		DataLoader dataLoader = new DataLoaderImpl();
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		System.out.println("\t\t\t\t\t\tFileERROR\n"+dataLoader.getFileError());
	}
}
