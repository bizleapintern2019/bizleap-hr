package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.domain.entity.Company;
import com.bizleap.domain.entity.Employee;

public interface DataManager {
	public String loadData();
	public List<Employee> getEmployeeList();
	public List<Company> getCompanyList();
}
