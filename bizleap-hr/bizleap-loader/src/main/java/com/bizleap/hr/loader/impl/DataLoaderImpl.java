package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {

	FileLoader fileLoader = new FileLoaderImpl();
	DataManager dataManager = new DataManagerImpl();
	private Map<Integer, Error> errorMap;

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("D:\\BizLeap/bizleap-workspace/Employee.txt");
		String line = null;
		Employee employee = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				employee = Employee.parseEmployee(line, fileLoader.getLineNumber());
				if (employee != null)
					dataManager.getEmployeesList().add(employee);
			} catch (Exception e) {
				errorException(employee, e);
			}
		}
		fileLoader.stop();
		return dataManager.getEmployeesList();
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("D:\\BizLeap/bizleap-workspace/Company.txt");
		String line = null;
		Company company = null;

		while (fileLoader.hasNext()) {
			try {
				line = fileLoader.getLine();
				company = Company.parseCompany(line);
				if (company != null)
					dataManager.getCompanyList().add(company);
			} catch (Exception e) {
				errorException(line, e);
			}
		}
		fileLoader.stop();
		return dataManager.getCompanyList();
	}

	public void errorException(Object source, Exception e) {
		if (!isError())
			errorMap = new HashMap<Integer, Error>();
		int lineNumber = fileLoader.getLineNumber();
		errorMap.put(lineNumber, new Error(lineNumber, source, e.toString()));
	}

	public Map<Integer, Error> getFileError() {
		return errorMap;
	}

	public boolean isError() {
		return getFileError() != null && !getFileError().isEmpty();
	}
}