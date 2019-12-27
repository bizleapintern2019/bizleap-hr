package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.internhr.service.resource.PositionServiceResource;
import com.bizleap.service.PositionService;

@RestController
@RequestMapping(value= {"/positions"})
public class PositionServiceResourceImpl implements PositionServiceResource {
	
	private Logger logger = Logger.getLogger(LocationServiceResourceImpl.class);
	@Autowired
	PositionService positionService;
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Position> getAllPosition(HttpServletRequest request) throws ServiceUnavailableException {
		logger.info("Size "+positionService.getAll().size());
		return positionService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createPosition(HttpServletRequest request,@RequestBody Position position) {
			try {
				positionService.savePosition(position);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody Position findByPositionBoId(
			HttpServletRequest request,
			@PathVariable("boId") String boId) throws ServiceUnavailableException {
		return positionService.findByBoId(boId);
	}
}
