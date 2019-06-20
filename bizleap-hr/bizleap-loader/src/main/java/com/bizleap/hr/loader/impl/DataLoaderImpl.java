package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.loader.FileLoader;

@Service
public class DataLoaderImpl implements DataLoader {
	
	@Autowired
	FileLoader fileLoader;
	
	@Autowired
	ErrorHandler errorHandler;
	
	int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
				errorHandler.handleLoadingError(++index, fileLoader.getLineNumber(), "Employee file loading.", dataLine);
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
				errorHandler.handleLoadingError(++index, fileLoader.getLineNumber(), "Company file loading.", dataLine);
			}
		}
		fileLoader.finish();
		return companyList;
	}
}