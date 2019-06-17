package com.bizleap.hr.loader;

import java.util.HashMap;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;

public interface DataLoader {
	public List<Employee> loadEmployee() throws Exception;

	public List<Company> loadCompany() throws Exception;
	
	public HashMap<Integer, Error> getError();
}
