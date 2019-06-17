package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface DataManager {
	public List<Employee> getEmployeesList();

	public List<Company> getCompanyList();

	public void loadData() throws Exception;
}
