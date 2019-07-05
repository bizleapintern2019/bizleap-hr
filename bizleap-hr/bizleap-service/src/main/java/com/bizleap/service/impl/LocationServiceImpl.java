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
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.LocationDao;
import com.bizleap.service.LocationService;

//@Author: Soe Min Thein
@Service
@Transactional(readOnly = false)
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;

	public void saveLocation(Location location) throws IOException, ServiceUnavailableException {
		locationDao.save(location);
	}

	public List<Location> getAll() throws ServiceUnavailableException {

		List<Location> locationList = locationDao.getAll("from Location ");
		if(CollectionUtils.isNotEmpty(locationList)) {
			hibernateInitializedList(locationList);
			return locationList;
		}
		return null;
	}

	public List<Location> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Location location where location.boId=:dataInput";
		List<Location> locationList = locationDao.findByString(query, boId);
		if(CollectionUtils.isNotEmpty(locationList)) {
			hibernateInitializedList(locationList);
			return locationList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Location> findByName(String name) throws ServiceUnavailableException {

		String query = "from Location where Location.name=:dataInput";
		List<Location> locationList = locationDao.findByString(query, name);
		hibernateInitializedList(locationList);
		return locationList;
	}

	public void hibernateInitializedList(List<Location> locationList) {
		for(Location location : locationList) {
			Hibernate.initialize(location.getDepartmentList());
			for(Department department : location.getDepartmentList()) {
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
		}
	}
}

