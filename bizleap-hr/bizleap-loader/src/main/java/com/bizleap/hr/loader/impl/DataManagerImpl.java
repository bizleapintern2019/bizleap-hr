package com.bizleap.hr.loader.impl;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;

public class DataManagerImpl implements DataManager {
	private List<Employee> employeeList;
	private List<Company> companyList;
	
	public DataManagerImpl() {}
	
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

	public void loadData() throws Exception {
		DataLoader dataLoader = new DataLoaderImpl();
		employeeList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
	}
}
