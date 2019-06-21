package com.bizleap.service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface SaverJDBC {
	public void saveEmployee(Employee employee);
	public void saveCompany(Company company);
}
