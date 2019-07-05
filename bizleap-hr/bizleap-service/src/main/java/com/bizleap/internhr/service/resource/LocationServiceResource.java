package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface LocationServiceResource {

	List<Location> getAllLocations(HttpServletRequest request); 
	boolean createLocation(HttpServletRequest request,Location location);
	List<Location> findByLocationBoId(HttpServletRequest request,String boId) throws ServiceUnavailableException;
	List<Location> findByLocationName(HttpServletRequest request,String name) throws ServiceUnavailableException;
}
