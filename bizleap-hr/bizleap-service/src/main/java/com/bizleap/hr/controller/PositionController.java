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

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.PositionService;

@Controller
@RequestMapping(value = "/position")
public class PositionController {

	private static Logger logger = Logger.getLogger(PositionController.class);

	@Autowired
	private PositionService positionService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllPositions(HttpServletRequest request, Model model) {

		List<Position> positionList = new ArrayList<Position>();

		try {
			positionList = positionService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		model.addAttribute("positionList", positionList);
		return "content";
	}
}