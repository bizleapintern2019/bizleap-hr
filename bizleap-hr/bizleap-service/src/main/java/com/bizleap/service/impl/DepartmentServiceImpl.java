package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.DepartmentDao;
import com.bizleap.service.DepartmentService;

//@Author: Soe Min Thein
@Service
//@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

	public void saveDepartment(Department department) throws IOException, ServiceUnavailableException {
		departmentDao.save(department);
	}

	public List<Department> getAll() throws ServiceUnavailableException {
		
		List<Department> departmentList = departmentDao.getAll("From Department department");
		return departmentList;
	}

	@Transactional(readOnly = true)
	public List<Department> findByBoId(String boId) throws ServiceUnavailableException {
		
		
		String query = "select department from Department department where department.boId=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, boId);
		hibernateInitializedList(departmentList);
		return departmentList;
	}
	
	public void hibernateInitializedList(List<Department> departmentList) {
		for(Department department : departmentList) {
			Hibernate.initialize(department.getJobList());
			for(Job job : department.getJobList()) {
				Hibernate.initialize(job.getPositionList());
				for(Position position : job.getPositionList()) {
					Hibernate.initialize(position.getReportToList());
					Hibernate.initialize(position.getReportByList());
				}
			}
		}
	}
}