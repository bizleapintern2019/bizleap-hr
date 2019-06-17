package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;


public class DataLoaderImpl implements DataLoader{

	FileLoader fileLoader = new FileLoaderImpl();
	DataManager dataManager = new DataManagerImpl();
	private HashMap<Integer, Error> errorHashMap;

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("E:\\Eclipse Project/employee.txt");
		String line = null;
		Employee employee = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				employee = Employee.parseEmployee(line,fileLoader.getLineNumber());
				if (employee != null)
					dataManager.getEmployeesList().add(employee);
			} catch (Exception e) {
				if(errorHashMap == null)
					errorHashMap = new HashMap<Integer, Error>();
				int lineNumber = fileLoader.getLineNumber();
				errorHashMap.put(lineNumber, new Error(lineNumber,employee,e.toString()));
			}
		}
		fileLoader.stop();
		return dataManager.getEmployeesList();
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("E:\\Eclipse Project/company.txt");
		String line = null;
		Company company = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				company = Company.parseCompany(line);
				if (company != null)
					dataManager.getCompanyList().add(company);
			} catch (Exception e) {
				int lineNumber = fileLoader.getLineNumber();
				errorHashMap.put(lineNumber, new Error(lineNumber,company,e.toString()));
			}
		}
		fileLoader.stop();
		return dataManager.getCompanyList();
	}
	
	public HashMap<Integer, Error> getError() {
		return errorHashMap;
	}
}