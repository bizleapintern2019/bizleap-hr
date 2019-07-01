package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.PositionDao;
import com.bizleap.service.PositionService;

//@Author: Kay Zin Han
@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;

	public void savePosition(Position position) throws IOException, ServiceUnavailableException {
		positionDao.save(position);
	}

	public List<Position> getAll() throws ServiceUnavailableException {
		
		List<Position> positionList = positionDao.getAll("From Position location");
		return positionList;
	}

	public List<Position> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "select position from Position position where location.boId=:dataInput";
		List<Position> positionList = positionDao.findByString(query, boId);
		return positionList;
	}
}