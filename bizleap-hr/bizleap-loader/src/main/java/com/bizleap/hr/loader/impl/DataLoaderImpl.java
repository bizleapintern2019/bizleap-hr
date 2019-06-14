package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {
	FileLoader fileLoader= new FileLoaderImpl();

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("D://Emp.txt");
		String dataLine="";
		String lineNumber="";
		List<Employee> employeeList= new ArrayList<Employee>();
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				lineNumber=fileLoader.getLineNumber()+"";
				/*if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}*/
				Employee employee = Employee.parseEmployee(dataLine);
				if(employee != null){
					employeeList.add(employee);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileLoader.finish();
		return employeeList;
	}

	public List<Company> loadCompany() throws Exception {
		fileLoader.start("D://Com.txt");
		String dataLine="";
		String lineNumber="";
		List<Company> companyList= new ArrayList<Company>();
		while(fileLoader.hasMore()) {
			try {
				dataLine=fileLoader.getLine();
				lineNumber=fileLoader.getLineNumber()+"";
				/*if(dataLine.startsWith("#")){
					if(fileLoader.hasMore())
					dataLine=fileLoader.getLine();
				}*/
				Company company = Company.parseCompany(dataLine);
				if(company != null){
					companyList.add(company);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileLoader.finish();
		return companyList;
	}

}
