package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DepartmentServiceResource {
	
	List<Department> getAllDepartments(HttpServletRequest request) throws ServiceUnavailableException;
	boolean createDepartment(HttpServletRequest request,Department department);
	List<Department> findByDepartmentBoId(HttpServletRequest request,String boId) throws ServiceUnavailableException;
	List<Department> findByDepartmentName(HttpServletRequest request,String name) throws ServiceUnavailableException;
}