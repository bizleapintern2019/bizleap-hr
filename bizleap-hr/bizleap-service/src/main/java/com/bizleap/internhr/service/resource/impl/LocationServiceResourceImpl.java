package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	LocationService locationService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Location> getAllLocations(HttpServletRequest request) throws ServiceUnavailableException {
		return locationService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createParticipant(HttpServletRequest request,@RequestBody Location location) {
			try {
				locationService.saveLocation(location);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public @ResponseBody List<Location> findByLocationBoId(HttpServletRequest request,
			@RequestHeader(value = "tenantId") String tenantId, @RequestHeader(value = "userId") String userId,
			@RequestParam(value = "boId") String boId) throws ServiceUnavailableException {
		return locationService.findByBoId(boId);
	}

}
