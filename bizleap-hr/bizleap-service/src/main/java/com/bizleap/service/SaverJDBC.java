package com.bizleap.service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface SaverJDBC {
	public void saveCompany(Company company);
	public void saveEmployee(Employee employee);
}
