package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;

public interface DataLoader {
	public List<Employee> loadEmployee() throws Exception;
	public List<Company> loadCompany() throws Exception;
	public int getIndex();
	public void setIndex(int index);
	public ErrorCollector getErrorCollection();
	public void setErrorCollection(ErrorCollector errorCollection);
	public Map<Integer, Error> getErrorHashMap();
	public void setErrorHashMap(Map<Integer, Error> errorHashMap);
}
