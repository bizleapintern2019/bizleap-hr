package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;
import com.bizleap.commons.domain.entity.Error;

public class DataLoaderImpl implements DataLoader {
	FileLoader fileLoader = new FileLoaderImpl();
	DataManager dataManager = new DataManagerImpl();
	private Map<Integer, Error> errorMap;
	private int index = 0;
	
	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("E:\\bizleap english\\employeeinformation.txt");
		String line = null;
		Employee employee = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				employee = Employee.parseEmployee(line, fileLoader.getLineNumber());
				if (employee != null)
					dataManager.getEmployeesList().add(employee);
			} catch (Exception e) {
				handleFileError(fileLoader.getLineNumber(), e.toString(), line);
			}
		}
		fileLoader.stop();
		return dataManager.getEmployeesList();
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("E:\\bizleap english\\companyinformation.txt");
		String line = null;
		Company company = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				company = Company.parseCompany(line);
				if (company != null)
					dataManager.getCompanyList().add(company);
			} catch (Exception e) {
				handleFileError(fileLoader.getLineNumber(), e.toString(), line);
			}
		}
		fileLoader.stop();
		return dataManager.getCompanyList();
	}

	public void handleFileError(int lineNumber, String message, String source) {
		if (errorMap == null)
			errorMap = new HashMap<Integer, Error>();

		errorMap.put(index++, new Error(lineNumber, source, message));
	}

	public Map<Integer, Error> getFileError() {
		return errorMap;
	}
}



