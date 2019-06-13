package com.bizleap.hr.loader;

import java.util.ArrayList;
import java.util.List;

import com.bizleap.domains.entity.Company;
import com.bizleap.domains.entity.Employee;

public class DataLoaderImpl implements DataLoader {

	FileLoaderImpl fileLoader = new FileLoaderImpl();

	public List<Employee> loadEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee;
		String eachLine = null;
		fileLoader.start("D://Emp.txt");
		while (fileLoader.hasNext()) {
			try {
				eachLine = fileLoader.getLine();
				employee = Employee.parseEmployee(eachLine);
				if (employee != null)
					employeeList.add(employee);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		fileLoader.stop();
		return employeeList;
	}

	public List<Company> loadCompany() throws Exception {
		List<Company> companyList = new ArrayList<Company>();
		Company company;
		String eachLine = null;
		fileLoader.start("D://Com.txt");
		while (fileLoader.hasNext()) {
			try {
				eachLine = fileLoader.getLine();
				company = Company.parseCompany(eachLine);
				if (company != null)
					companyList.add(company);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		fileLoader.stop();
		return companyList;
	}
}