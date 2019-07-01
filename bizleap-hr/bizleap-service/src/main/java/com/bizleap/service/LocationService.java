package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface LocationService {
	
	void saveLocation(Location location) throws IOException, ServiceUnavailableException;
	List<Location> getAll() throws ServiceUnavailableException;
	List<Location> findByBoId(String boId) throws ServiceUnavailableException;
}