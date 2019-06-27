package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.DepartmentDao;
import com.bizleap.service.DepartmentService;

//@Author: Soe Min Thein
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public void saveDepartment(Department department) throws IOException, ServiceUnavailableException {
		departmentDao.save(department);
	}
}