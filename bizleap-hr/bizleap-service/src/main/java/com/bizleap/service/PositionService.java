package com.bizleap.service;

import java.io.IOException;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface PositionService {
	void saveCompany(Position position) throws IOException, ServiceUnavailableException;

}
