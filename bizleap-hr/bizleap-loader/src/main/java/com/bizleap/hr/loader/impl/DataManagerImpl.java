package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;
import com.bizleap.service.Saver;
import com.bizleap.service.impl.SaverImpl;


public class DataManagerImpl implements DataManager {
	private List<Employee> employeesList = new ArrayList<Employee>();
	private List<Company> companyList = new ArrayList<Company>();

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void loadData() throws Exception {
		FileLoader fileLoader = new FileLoaderImpl();
		DataLoader dataLoader = new DataLoaderImpl(fileLoader);
		employeesList = dataLoader.loadEmployee();
		companyList = dataLoader.loadCompany();
		if(fileLoader.hasError()) {
			System.out.println("\t\t\t\t\t\tFileERROR\n"+dataLoader.getFileError());
			System.exit(0);
		}
	}

	public void associateData() {
		AssociationMapper associationMapper = new AssociationMapperImpl(this);
		associationMapper.setUpAssociations();
		if(associationMapper.hasError()) {
			System.out.println("\t\t\t\t\t\tLinkedERROR\n" +associationMapper.getLinkedErrorMap());
			System.exit(0);
		}
	}
		public void saveData() {
			Saver saver = new SaverImpl();
			saver.saveCompanies(companyList);
			saver.saveEmployees(employeesList);
		}

	public void load() throws Exception{
		loadData();
		associateData();
		saveData();
	}
}
