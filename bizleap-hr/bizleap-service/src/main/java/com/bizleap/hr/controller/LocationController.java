package com.bizleap.hr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.LocationService;

@Controller
@RequestMapping(value = "/location")
public class LocationController {
	
	private static Logger logger = Logger.getLogger(LocationController.class);
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllLocations(HttpServletRequest request, Model model) {
		
		List<Location> locationList = new ArrayList<Location>();
		
		try {
			locationList = locationService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}
		
		model.addAttribute("locationList", locationList);
		return "content";
	}

}
