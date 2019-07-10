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

	@Transactional(readOnly = true)
	public List<Department> getAllDepartment() throws ServiceUnavailableException {

		List<Department> departmentList = departmentDao.getAll("from Department ");
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Department> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Department department where department.boId=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, boId);
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Department> findByName(String name) throws ServiceUnavailableException {

		String query = "from Department department where department.name=:dataInput";
		List<Department> departmentList = departmentDao.findByString(query, name);
		if (CollectionUtils.isNotEmpty(departmentList)) {
			hibernateInitializedDepartmentList(departmentList);
			return departmentList;
		}
		return null;
	}

	public void hibernateInitializedPosition(Position position) {
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
	}

	public void hibernateInitializedDepartment(Department department) {
		Hibernate.initialize(department);
		hibernateInitializedJobList(department.getJobList());
	}

	public void hibernateInitializedDepartmentList(List<Department> departmentList) {
		for (Department department : departmentList) {
			hibernateInitializedDepartment(department);
		}
	}
	
	/*public void hibernateInitializedDepartmentList(List<Department> departmentList) {
		for(Department department : departmentList) {
			Hibernate.initialize(department);
			Hibernate.initialize(department.getJobList());
			for(Job job : department.getJobList()) {
				Hibernate.initialize(job.getPositionList());
				for(Position position : job.getPositionList()) {
					Hibernate.initialize(position.getReportToList());
					Hibernate.initialize(position.getReportByList());
				}
			}
		}
	}*/
}