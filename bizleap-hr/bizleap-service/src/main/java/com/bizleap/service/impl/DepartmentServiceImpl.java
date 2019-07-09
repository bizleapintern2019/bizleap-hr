package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
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
// @Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	public void saveDepartment(Department department) throws IOException, ServiceUnavailableException {
		departmentDao.save(department);
	}

	public List<Department> getAllDepartment() throws ServiceUnavailableException {

		List<Department> departmentList = departmentDao.getAll("From Department department");
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedList(departmentList);
			return departmentList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Department> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "select department from Department department where department.boId=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, boId);
		hibernateInitializedList(departmentList);
		return departmentList;
	}

	@Transactional(readOnly = true)
	public List<Department> findByName(String name) throws ServiceUnavailableException {

		String query = "select department from Department department where department.name=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, name);
		hibernateInitializedList(departmentList);
		return departmentList;
	}

	public void hibernateInitializedPosition(Position position) {
		Hibernate.initialize(position);
	}

	public void hibernateInitializedPositionList(List<Position> positionList) {
		for (Position position : positionList) {
			Hibernate.initialize(position.getReportToList());
			Hibernate.initialize(position.getReportByList());
		}
	}

	public void hibernateInitializedJob(Job job) {
		Hibernate.initialize(job);
	}

	public void hibernateInitializedJobList(List<Job> jobList) {
		for (Job job : jobList) {
			Hibernate.initialize(job.getPositionList());
		}
	}

	public void hibernateInitializedDepartment(Department department) {
		Hibernate.initialize(department);
		Hibernate.initialize(department.getJobList());

	}

	public void hibernateInitializedList(List<Department> departmentList) {
		for (Department department : departmentList) {
			hibernateInitializedDepartment(department);
		}
	}
}