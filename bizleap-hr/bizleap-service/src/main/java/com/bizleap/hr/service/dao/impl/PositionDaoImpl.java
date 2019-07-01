package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import com.bizleap.hr.service.dao.PositionDao;

//@Author: Kay Zin Han
@Repository
public class PositionDaoImpl extends AbstractDaoImpl<Position, String> implements PositionDao {
	
	protected PositionDaoImpl() {
		super(Position.class);
	}

	@Override
	public void save(Position position) throws ServiceUnavailableException {
		saveOrUpdate(position);
	}
}