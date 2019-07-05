package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bizleap.commons.domain.entity.Location;

public interface LocationServiceResource {

	public List<Location> getAllLocations(HttpServletRequest request); 
}
