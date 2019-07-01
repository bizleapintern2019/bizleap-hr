package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.DepartmentDao;

//@Author: Soe Min Thein
@Repository
public class DepartmentDaoImpl extends AbstractDaoImpl<Department, String> implements DepartmentDao {

	protected DepartmentDaoImpl() {
		super(Department.class);
	}

	public void save(Department department) throws ServiceUnavailableException {
		saveOrUpdate(department);
	}
}