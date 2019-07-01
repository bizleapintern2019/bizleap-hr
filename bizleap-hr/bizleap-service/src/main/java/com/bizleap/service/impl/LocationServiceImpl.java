package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.LocationDao;
import com.bizleap.service.LocationService;

//@Author: Soe Min Thein
@Service
//@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;

	public void saveLocation(Location location) throws IOException, ServiceUnavailableException {
		locationDao.save(location);
	}

	public List<Location> getAll() throws ServiceUnavailableException {
		
		List<Location> locationList = locationDao.getAll("From Location location");
		return locationList;
	}

	public List<Location> findByBoId(String boId) throws ServiceUnavailableException {
		
		String query = "select location from Location location where location.boId=:dataInput";
		List<Location> locationList = locationDao.findByString(query, boId);
		return locationList;
	}
}