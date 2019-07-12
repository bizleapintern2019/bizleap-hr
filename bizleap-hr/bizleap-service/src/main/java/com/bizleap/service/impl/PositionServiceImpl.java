package com.bizleap.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.PositionDao;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.PositionService;

//@Author: Kay Zin Han
@Service
// @Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private EmployeeService employeeService;
	
	public void savePosition(Position position) throws IOException, ServiceUnavailableException {
		positionDao.save(position);
	}
	
	@Transactional(readOnly = true)
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException {
		List<Position> positionList = positionDao.getAll("from Position");
		if(!CollectionUtils.isEmpty(positionList)) {
			hibernateInitializedList(positionList);	

			List<AbstractEntity> entityList = new ArrayList<AbstractEntity>();
			entityList.addAll(getAll());
			return entityList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Position> getAll() throws ServiceUnavailableException {
		
		List<Position> positionList = positionDao.getAll("From Position");
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
	
	@Transactional(readOnly = true)
	public Position findPositionByEmployeeBoId(String employeeBoId) throws ServiceUnavailableException {
		Employee employee = employeeService.findByBoId(employeeBoId);
		String query = "from Position position where position.employee.id=:dataInput";
		List<Position> positionList = positionDao.findByLong(query, employee.getId());
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