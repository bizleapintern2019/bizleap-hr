package com.bizleap.hr.service.dao.impl;


import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.LocationDao;

@Repository
public class LocationDaoImpl extends AbstractDaoImpl<Location,String> implements LocationDao{
	 protected LocationDaoImpl() {
	        super(Location.class);
	    }

	    public void save(Location location) throws ServiceUnavailableException {
	        saveOrUpdate(location);
	    }
}