package com.bizleap.hr.loader.impl;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

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

	public void loadData() {
		DataLoader dataLoader = new DataLoaderImpl();
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
		} catch (Exception e) {
			System.out.println(e+ " ");
		}
	}
	
	private void associateData() {
		AssociationMapper associationMapper = new AssociationMapperImpl();
		associationMapper.setUpAssociations();
	}
	
	private void saveData () {
		Saver saver = new SaverImpl();
		for(Company company : companyList) {
			if(company != null)
				saver.companySave(company);
		}
		for(Employee employee : employeeList) {
			if(employee != null)
				saver.employeesave(employee);
		}
	}
	
	public void load() {
		loadData();
		associateData();
		saveData();
	}
}
