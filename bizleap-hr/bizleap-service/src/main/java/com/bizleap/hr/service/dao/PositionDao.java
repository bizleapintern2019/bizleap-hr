package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface PositionDao extends AbstractDao<Position, String>{
	 public void save(Position position) throws ServiceUnavailableException;
}
