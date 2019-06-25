package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.loader.FileLoader;

@Service
public class DataLoaderImpl implements DataLoader {

	@Autowired
	private FileLoader fileLoader;

	@Autowired
	private ErrorHandler errorHandler;

	private Map<Integer,Error> errorMap = new HashMap<>();
	int index =0;

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
				errorHandler.handleLoadingError(index,fileLoader.getLineNumber(),"Company file loading.",dataLine);
				index++;

			}
		}
		fileLoader.finish();
		return companyList;
	}

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
				errorHandler.handleLoadingError(index,fileLoader.getLineNumber(),"Employee file loading.",dataLine);
				index++;
			}
		}
		fileLoader.finish();
		return employeeList;
	}
}