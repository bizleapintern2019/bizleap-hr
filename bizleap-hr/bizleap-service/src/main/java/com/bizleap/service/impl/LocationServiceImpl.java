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
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.LocationDao;
import com.bizleap.service.DepartmentService;
import com.bizleap.service.LocationService;

//@Author: Soe Min Thein
@Service
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Transactional(readOnly = false)
	public void saveLocation(Location location) throws IOException, ServiceUnavailableException {
		locationDao.save(location);
	}
	
	@Transactional(readOnly = true)
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException {
		List<Location> locationList = locationDao.getAll("from Location");
		if(!CollectionUtils.isEmpty(locationList)) {
			hibernateInitializedLocationList(locationList);	

			List<AbstractEntity> entityList = new ArrayList<AbstractEntity>();
			entityList.addAll(getAll());
			return entityList;
		}
		return null;
	}

	public List<Location> getAll() throws ServiceUnavailableException {

		List<Location> locationList = locationDao.getAll("from Location ");
		if(CollectionUtils.isNotEmpty(locationList)) {
			hibernateInitializedLocationList(locationList);
			return locationList;
		}
		return null;
	}

	public List<Location> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Location location where location.boId=:dataInput";
		List<Location> locationList = locationDao.findByString(query, boId);
		if(CollectionUtils.isNotEmpty(locationList)) {
			hibernateInitializedLocationList(locationList);
			return locationList;
		}
		return null;
	}

	public List<Location> findByName(String name) throws ServiceUnavailableException {

		String query = "from Location location where location.name=:dataInput";
		List<Location> locationList = locationDao.findByString(query, name);
		if(CollectionUtils.isNotEmpty(locationList)) {
			hibernateInitializedLocationList(locationList);
			return locationList;
		}
		return null;
	}
	
	/*public void hibernateInitializedPosition(Position position) {
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
	}*/

	public void hibernateInitializedLocation(Location location) {
		Hibernate.initialize(location);
		departmentService.hibernateInitializedDepartmentList(location.getDepartmentList());
	}

	public void hibernateInitializedLocationList(List<Location> locationList) {
		for (Location location : locationList) {
			hibernateInitializedLocation(location);
		}
	}
}

