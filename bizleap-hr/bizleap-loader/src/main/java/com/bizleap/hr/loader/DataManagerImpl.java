package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.domains.entity.Company;
import com.bizleap.domains.entity.Employee;

public class DataManagerImpl implements DataManager {
	DataLoaderImpl dataLoader = new DataLoaderImpl();
	List<Employee> employeeList;
	List<Company> companyList;
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList=employeeList;
	}
	
	public List<Company> getCompanyList(){
		return companyList;
	}
	
	public void setCompanyList(List<Company> companyList) {
		this.companyList=companyList;
	}

	public String loadData() {
		String result = "";
		try {
			employeeList = dataLoader.loadEmployee();
			companyList = dataLoader.loadCompany();
			for (Company company : companyList) {
				for (Employee employee : employeeList) {
					if (company.getBoId().equals(employee.getBoId())) {
						result += employee.getFirstName() + " " + employee.getLastName() + "---- works for "
								+ company.getName() + "\n";
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
