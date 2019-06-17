package com.bizleap.hr.loader.impl;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataManager;

public class DataManagerImpl implements DataManager {

	DataLoaderImpl dataLoader = new DataLoaderImpl();
	
	List<Employee> employeeList;
	List<Company> companyList;

	public DataLoaderImpl getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoaderImpl dataLoader) {
		this.dataLoader = dataLoader;
	}

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

	public void loadData() {
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		}
		catch (Exception ex) {
			System.out.println(ex + "");
		}
	}
}