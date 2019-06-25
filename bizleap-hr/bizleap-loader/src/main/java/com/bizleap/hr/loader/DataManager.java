package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface DataManager {
	public void loadData() throws ServiceUnavailableException, IOException, Exception;
	public List<Employee> getEmployeeList();
	public List<Company> getCompanyList();
	public void saveData();
	public void load();
}
