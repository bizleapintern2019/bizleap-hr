package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface PositionService extends DataService {
	
	void savePosition(Position position) throws IOException, ServiceUnavailableException;
	List<Position> getAll() throws ServiceUnavailableException;
	Position findByBoId(String boId) throws ServiceUnavailableException;
	Position findPositionByEmployeeBoId(String employeeBoId) throws ServiceUnavailableException;
	void hibernateInitializedList(List<Position> positionList);
}