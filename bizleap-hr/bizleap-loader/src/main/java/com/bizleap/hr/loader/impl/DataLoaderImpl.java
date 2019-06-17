package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {

	FileLoader fileLoader= new FileLoaderImpl();
	
	Map<Integer,Error> errorMap = new HashMap<Integer, Error>();

	int index = 1;
	
	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorHashMap) {
		this.errorMap = errorHashMap;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void handleLoadingError(int lineNumber, String message, Object source) {
		Error error = new Error(lineNumber, source, message);
		
		if(errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index++, error);
	}

	public List<Employee> loadEmployee() throws Exception {
		
		fileLoader.start("E:\\Example\\Employees.txt");
		String dataLine = "";
		
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = null;
		
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				
				if(dataLine.startsWith("#")) {
					
					if(fileLoader.hasMore())
					dataLine = fileLoader.getLine();
				}
				
				employee = Employee.parseEmployee(dataLine);
				
				if(employee != null){
					employeeList.add(employee);
				}
				
			}
			catch (Exception e) {
				handleLoadingError(fileLoader.getLineNumber(), "Employee file loading.", dataLine);
			}
		}
		fileLoader.finish();
		return employeeList;
	}

	public List<Company> loadCompany() throws Exception {
		
		fileLoader.start("E:\\Example\\Companies.txt");
		String dataLine = "";
		
		List<Company> companyList = new ArrayList<Company>();
		Company company = null;
		
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				
				if(dataLine.startsWith("#")) {
					
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}
				
				company = Company.parseCompany(dataLine);
				
				if(company != null) {
					companyList.add(company);
				}
				
			}
			catch (Exception e) {
				handleLoadingError(fileLoader.getLineNumber(), "Company file loading.", dataLine);
			}
		}
		fileLoader.finish();
		return companyList;
	}
}