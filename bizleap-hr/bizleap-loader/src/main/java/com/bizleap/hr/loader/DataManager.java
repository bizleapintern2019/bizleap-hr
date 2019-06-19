package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface DataManager {
	public void loadData();
	public List<Employee> getEmployeeList();
	public List<Company> getCompanyList();
	public DataLoader getDataLoader();
	public ErrorHandler getErrorHandler();
	public void save();
	public void load();
}
