package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.LoadingError;

public interface DataManager {
	public void loadData();
	public List<Employee> getEmployeeList();
	public List<Company> getCompanyList();
	public Map<Integer,LoadingError> getErrorMap();
	public void setErrorMap(Map<Integer,LoadingError> errorMap);
//	public void saveData();
	public void load();
}