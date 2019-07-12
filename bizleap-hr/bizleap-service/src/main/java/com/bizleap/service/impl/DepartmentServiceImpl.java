package com.bizleap.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.DepartmentDao;
import com.bizleap.service.DepartmentService;
import com.bizleap.service.JobService;


//@Author: Soe Min Thein
@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired 
	private JobService jobService;

	@Transactional(readOnly = false)
	public void saveDepartment(Department department) throws IOException, ServiceUnavailableException {
		departmentDao.save(department);
	}
	
	@Transactional(readOnly = true)
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException {
		List<Department> departmentList = departmentDao.getAll("from Department");
		if(!CollectionUtils.isEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);	

			List<AbstractEntity> entityList = new ArrayList<AbstractEntity>();
			entityList.addAll(getAll());
			return entityList;
		}
		return null;
	}
	
	public List<Department> getAll() throws ServiceUnavailableException {

		List<Department> departmentList = departmentDao.getAll("from Department ");
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

	public List<Department> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Department department where department.boId=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, boId);
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

	public List<Department> findByName(String name) throws ServiceUnavailableException {

		String query = "from Department department where department.name=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, name);
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

/*	public void hibernateInitializedPosition(Position position) {
		Hibernate.initialize(position);
	}

	public void hibernateInitializedPositionList(List<Position> positionList) {
		for (Position position : positionList) {
			hibernateInitializedPosition(position);
			Hibernate.initialize(position.getReportToList());
			Hibernate.initialize(position.getReportByList());
		}
	}

	public void hibernateInitializedJob(Job job) {
		Hibernate.initialize(job);
		hibernateInitializedPositionList(job.getPositionList());
	}

	public void hibernateInitializedJobList(List<Job> jobList) {
		for (Job job : jobList) {
			hibernateInitializedJob(job);
		}
	}*/

	public void hibernateInitializedDepartment(Department department) {
		Hibernate.initialize(department);
		jobService.hibernateInitializedList(department.getJobList());
	}

	public void hibernateInitializedDepartmentList(List<Department> departmentList) {
		for (Department department : departmentList) {
			hibernateInitializedDepartment(department);
		}
	}
}