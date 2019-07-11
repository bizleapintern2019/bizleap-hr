package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.PositionDao;

import com.bizleap.service.PositionService;

//@Author: Kay Zin Han
@Service
// @Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	public void savePosition(Position position) throws IOException, ServiceUnavailableException {
		positionDao.save(position);
	}
	
	@Transactional(readOnly = true)
	public List<Position> getAll() throws ServiceUnavailableException {
		
		List<Position> positionList = positionDao.getAll("From Position position");
		if(!CollectionUtils.isEmpty(positionList)){
			hibernateInitializedList(positionList);
			return positionList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Position findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Position position where position.boId=:dataInput";
		List<Position> positionList = positionDao.findByString(query, boId);
		if(!CollectionUtils.isEmpty(positionList)){
			hibernateInitializedList(positionList);
			return positionList.get(0);
		}
		return null;
	}

	public void hibernateInitializedList(List<Position> positionList) {

		for (Position position : positionList) {
			Hibernate.initialize(position.getReportToList());
			Hibernate.initialize(position.getReportByList());
		}
	}
}