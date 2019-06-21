package com.bizleap.service;

import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

public interface SaverJDBC {
	public void saveCompanies(List<Company> companies);

	public void saveEmployees(List<Employee> employees);
}
