package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;


public class DataManagerImpl implements DataManager {
	DataLoaderImpl dataLoader;
	Map<Integer,Error> errorMap = new HashMap<Integer,Error>();
	List<Employee> employeeList;
	List<Company> companyList;
	ErrorCollector errorCollector;
	
	public DataManagerImpl() {
		errorCollector =new ErrorCollectorImpl();
	}

	public DataLoaderImpl getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoaderImpl dataLoader) {
		this.dataLoader = dataLoader;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList=employeeList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList=companyList;
	}

	public void loadData() {
		try {
			dataLoader = new DataLoaderImpl(errorCollector);
			companyList = dataLoader.loadCompany();
			employeeList = dataLoader.loadEmployee();

		} catch (Exception ex) {
			System.out.println(ex+"");
		}
	}

	@Override
	public void save() {
		Saver save = new SaverImpl();
		
		for(Company company: companyList) {
			if(company != null) {
				save.saveCompany(company);
			}
		}
		
		for(Employee employee: employeeList) {
			if(employee != null) {
				save.saveEmployee(employee);
			}
		}
	}
	
	public void associateData() {
		if(!errorCollector.hasError()){
		AssociationMapper associationMapper = new AssociationMapperImpl(this,errorCollector);
		associationMapper.setUpAssociations();
		return;
		}
		System.out.println("Error occurs.");
	}

	public void load() {
		loadData();
		associateData();
		if(!errorCollector.hasError())
			save();
	}

	@Override
	public ErrorCollector getErrorCollector() {
		return errorCollector;
	}
}