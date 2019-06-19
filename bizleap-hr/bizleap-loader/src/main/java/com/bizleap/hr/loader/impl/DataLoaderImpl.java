package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.hr.loader.FileLoader;


public class DataLoaderImpl implements DataLoader {

	FileLoader fileLoader= new FileLoaderImpl();
	Map<Integer,Error> errorMap = new HashMap<>();
    DataManager dataManager;
	ErrorCollector errorCollector;
	int index =0;

	public DataLoaderImpl(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public ErrorCollector getErrorCollection() {
		return errorCollector;
	}

	public void setErrorCollection(ErrorCollector errorCollection) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void handleLoadingError(int lineNumber,String message, Object source) {
//		index++;
//		Error error= new Error(lineNumber,source,message);
//		if(errorMap == null) {
//			errorMap = new HashMap<Integer, Error>();
//		}
//		errorMap.put(index,error);
////		setErrorMap(errorMap);
//	}
//
//	public void handleLinkedError(String message, Object source) {
//		index++;
//
//		Error error= new Error(source,message);
//
//		if(errorMap == null) {
//			errorMap = new HashMap<Integer, Error>();
//		}
//		errorMap.put(index,error);
////		setErrorMap(errorMap);
//	}


	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("E:\\AAWE-1\\employee.txt");
		String dataLine="";
		List<Employee> employeeList= new ArrayList<Employee>();
		Employee employee = null;
		while(fileLoader.hasNext()) {
			try {
				dataLine=fileLoader.getLine();
				if(dataLine.startsWith("#") ) {
					if(fileLoader.hasNext())
						dataLine=fileLoader.getLine();
				}
				if(dataLine.startsWith(" ")) {
					return null;
				}
				employee = Employee.parseEmployee(dataLine);
				if(employee != null) {
					employeeList.add(employee);
				}
			}catch (Exception e) {
				errorCollector.handleLoadingError(index,fileLoader.getLineNumber(),"Employee file loading.",dataLine);
				index++;
			}
		}
		fileLoader.finish();
		return employeeList;
	}


	public List<Company> loadCompany() throws Exception {
		fileLoader.start("E:\\AAWE-1\\company.txt");
		String dataLine="";
		List<Company> companyList= new ArrayList<Company>();
		Company company=null;
		while(fileLoader.hasNext()) {
			try {
				dataLine=fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasNext())
						dataLine=fileLoader.getLine();
				}
				if(dataLine.startsWith(" ")) {
					return null;
				}
				company = Company.parseCompany(dataLine);
				if(company != null) {
					companyList.add(company);
				}
			}catch (Exception e) {
				errorCollector.handleLoadingError(index,fileLoader.getLineNumber(),"Company file loading.",dataLine);
				index++;

			}
		}
		fileLoader.finish();
		return companyList;
	}



}