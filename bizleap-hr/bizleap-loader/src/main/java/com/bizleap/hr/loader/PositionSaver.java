package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface PositionSaver {
	void savePass1() throws ServiceUnavailableException, IOException;
	void setPositionList(List<Position> positionList);
}