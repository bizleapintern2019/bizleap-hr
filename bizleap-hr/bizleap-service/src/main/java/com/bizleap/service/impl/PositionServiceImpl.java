package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.CompanyDao;
import com.bizleap.hr.service.dao.PositionDao;
import com.bizleap.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionDao positionDao;

	@Override
	public void saveCompany(Position position) throws IOException, ServiceUnavailableException {
		positionDao.save(position);
	}
}