package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {
	FileLoader fileLoader= new FileLoaderImpl();
	private DataManager dataManager;
	ErrorCollector errorCollector;
	public Map<Integer,ErrorCollection> errorHashMap;
	public int index =1;

	public DataLoaderImpl(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public Map<Integer, ErrorCollection> getErrorHashMap() {
		return errorHashMap;
	}

	public void setErrorHashMap(Map<Integer, ErrorCollection> errorHashMap) {
		this.errorHashMap = errorHashMap;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("D://Emp.txt");
		String dataLine="";
		List<Employee> employeeList= new ArrayList<Employee>();
		Employee employee = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				/*if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}*/
				employee = Employee.parseEmployee(dataLine);
				if(employee != null){
					employeeList.add(employee);
				}
			}catch (Exception e) {
				errorCollector.handleLoadingError(index,fileLoader.getLineNumber(),"Employee file loading.",dataLine);
				//System.out.println(index);
				index++;
			}
		}
		fileLoader.finish();
		return employeeList;
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("D://Com.txt");
		String dataLine="";
		List<Company> companyList= new ArrayList<Company>();
		Company company=null;
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				/*if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}*/
				company = Company.parseCompany(dataLine);
				if(company != null){
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

//	public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {
//		System.out.println("Index in Loading Error"+index);
//		ErrorCollection error= new ErrorCollection(indexNumber,source,message);
//		if(errorHashMap == null){
//			errorHashMap = new HashMap<Integer, ErrorCollection>();
//		}
//		errorHashMap.put(index,error);
//		
//	}
//
//	public void handleLinkedError(int indexNumer, String message, Object source) {
//		System.out.println("Index in Linked Error"+index);
//		
//		ErrorCollection error= new ErrorCollection(source,message);
//		if(errorHashMap == null){
//			errorHashMap = new HashMap<Integer, ErrorCollection>();
//		}
//		
//		errorHashMap.put(index,error);
//		
//	}

	public ErrorCollector getErrorCollection() {
		return errorCollector;
	}

	public void setErrorCollection(ErrorCollector errorCollection) {
		// TODO Auto-generated method stub
		
	}

}
