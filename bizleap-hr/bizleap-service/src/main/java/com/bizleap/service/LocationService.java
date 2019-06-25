package com.bizleap.service;

import java.io.IOException;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface LocationService {
	void saveLocation(Location location) throws IOException, ServiceUnavailableException;
}
