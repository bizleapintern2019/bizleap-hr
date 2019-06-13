package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.domain.entity.Company;
import com.bizleap.domain.entity.Employee;

public interface DataLoader {
	public List<Employee> loadEmployee() throws Exception;

	public List<Company> loadCompany() throws Exception;
}
