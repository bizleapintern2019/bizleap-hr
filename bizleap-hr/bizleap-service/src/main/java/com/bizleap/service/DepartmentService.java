package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DepartmentService extends DataService {
	
	void saveDepartment(Department department) throws IOException, ServiceUnavailableException;
	List<Department> getAll() throws ServiceUnavailableException;
	List<Department> findByBoId(String boId) throws ServiceUnavailableException;
	List<Department> findByName(String name) throws ServiceUnavailableException;
	void hibernateInitializedDepartmentList(List<Department> departmentList);
}