package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;

public class DataManagerImpl implements DataManager {
	DataLoaderImpl dataLoader;
	Map<Integer,ErrorCollection> errorMap = new HashMap<Integer,ErrorCollection>();
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

	public Map<Integer, ErrorCollection> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, ErrorCollection> errorMap) {
		this.errorMap = errorMap;
	}

	public ErrorCollector getErrorCollector() {
		return errorCollector;
	}

	public void setErrorCollector(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public void loadData() {
		try {
			dataLoader = new DataLoaderImpl(errorCollector);
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
			AssociationMapper associationMapper = new AssociationMapperImpl(this,errorCollector);
			associationMapper.setUpAssociations();
		} catch (Exception ex) {
			System.out.println(ex+"");
		}
	}
	public void save() {
		Saver save =new SaverImpl();
		for(Company company: companyList) {
			if(company!=null) {
				save.saveCompany(company);
			}
		}
		for(Employee employee: employeeList) {
			if(employee!=null) {
				save.saveEmployee(employee);
			}
		}
	}
	public void associateData() {
		AssociationMapper associationMapper = new AssociationMapperImpl(this);
		associationMapper.setUpAssociations();
	}
	public void load() {
		loadData();
		associateData();
		save();
	}
}
