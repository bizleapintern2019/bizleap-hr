package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;

@Service
public class DataLoaderImpl implements DataLoader {

	@Autowired
	private FileLoader fileLoader;
	
	@Autowired
	private DataManager dataManager;
	
	private Map<Integer, Error> errorMap;
	private int index = 0;

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("E:\\Eclipse Project/employee.txt");
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
				handleFileError(fileLoader.getLineNumber(), e.toString(), line);
			}
		}
		fileLoader.stop();
		return dataManager.getCompanyList();
	}

	public void handleFileError(int lineNumber, String message, String source) {
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}

		errorMap.put(index++, new Error(lineNumber, source, message));
	}

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}
}