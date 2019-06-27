package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.LocationDao;
import com.bizleap.service.LocationService;

//@Author: Soe Min Thein
@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationDao locationDao;

	@Override
	public void saveLocation(Location location) throws IOException, ServiceUnavailableException {
		locationDao.save(location);
	}
}