package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface LocationDao extends AbstractDao<Location,String>{
	public void save(Location location)throws ServiceUnavailableException;
}
