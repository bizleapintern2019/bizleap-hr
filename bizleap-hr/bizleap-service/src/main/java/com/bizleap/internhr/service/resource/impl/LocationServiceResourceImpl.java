package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.LocationService;

@RestController
@RequestMapping(value= {"/locations"})
public class LocationServiceResourceImpl {
	
	private Logger logger = Logger.getLogger(LocationServiceResourceImpl.class);
	@Autowired
	LocationService locationService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Location> getAllLocations(HttpServletRequest request) throws ServiceUnavailableException {
		logger.info("Size "+locationService.getAll().size());
		return locationService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createLocation(HttpServletRequest request,@RequestBody Location location) {
			try {
				locationService.saveLocation(location);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody List<Location> findByLocationBoId(
			HttpServletRequest request,
			@PathVariable(value = "boId") String boId) throws ServiceUnavailableException {
		return locationService.findByBoId(boId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public @ResponseBody List<Location> findByLocationName(
			HttpServletRequest request,
			@RequestParam(value = "name") String name) throws ServiceUnavailableException {
		return locationService.findByName(name);
	}
	
}
