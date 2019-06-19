package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {
	FileLoader fileLoader= new FileLoaderImpl();
	ErrorHandler errorHandler;
	public Map<Integer,Error> errorMap;
	public int index =1;

	public DataLoaderImpl(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
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

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("E:\\test\\EmployeeData.txt");
		String dataLine="";
		List<Employee> employeeList= new ArrayList<Employee>();
		Employee employee = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}
				else if(dataLine.startsWith("  ")){
					break;
				}
				employee = Employee.parseEmployee(dataLine);
				if(employee != null){
					employeeList.add(employee);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(index,fileLoader.getLineNumber(),"Employee file loading.",dataLine);
				index++;
			}
		}
		fileLoader.finish();
		return employeeList;
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("E:\\test\\CompanyData.txt");
		String dataLine="";
		List<Company> companyList= new ArrayList<Company>();
		Company company=null;
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}
				company = Company.parseCompany(dataLine);
				if(company != null){
					companyList.add(company);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(index,fileLoader.getLineNumber(),"Company file loading.",dataLine);
				index++;
				
			}
		}
		fileLoader.finish();
		return companyList;
	}

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	@Override
	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		
	}
}
