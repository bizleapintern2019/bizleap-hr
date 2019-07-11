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

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.AddressService;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

	private static Logger logger = Logger.getLogger(PositionController.class);

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllAddresses(HttpServletRequest request, Model model) {

		List<Address> addressList = new ArrayList<Address>();

		try {
			addressList = addressService.getAll();
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}

		model.addAttribute("addressList", addressList);
		return "content";
	}
}