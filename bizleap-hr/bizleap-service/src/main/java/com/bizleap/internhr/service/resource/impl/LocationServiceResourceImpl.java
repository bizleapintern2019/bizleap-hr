package com.bizleap.internhr.service.resource.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.LocationService;

@RestController
@RequestMapping(value= {"/locations"})
public class LocationServiceResourceImpl {
	
	@Autowired
	LocationService locationService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Location> getAllLocations(HttpServletRequest request) throws ServiceUnavailableException {
		return locationService.getAll();
	}

}
