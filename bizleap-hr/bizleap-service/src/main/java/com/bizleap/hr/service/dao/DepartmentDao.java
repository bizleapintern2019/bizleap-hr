package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface DepartmentDao extends AbstractDao<Department,String>{
	public void save(Department department)throws ServiceUnavailableException;
}
