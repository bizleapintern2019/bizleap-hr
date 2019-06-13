package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.domains.entity.Company;
import com.bizleap.domains.entity.Employee;

public interface DataLoader {
	public List<Employee> loadEmployee() throws Exception;

	public List<Company> loadCompany() throws Exception;
}
