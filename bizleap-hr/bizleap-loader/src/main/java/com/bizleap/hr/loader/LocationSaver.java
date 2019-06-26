package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface LocationSaver {
	void savePass1() throws ServiceUnavailableException, IOException;
	void setLocationList(List<Location> locationList);
}