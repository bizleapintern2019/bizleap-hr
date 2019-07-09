package com.bizleap.internhr.service.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.internhr.service.resource.AddressServiceResource;
import com.bizleap.service.AddressService;

@RestController
@RequestMapping(value= {"/address"})
public class AddressServiceResourceImpl implements AddressServiceResource {

	@Autowired
	AddressService addressService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	public @ResponseBody boolean createAddress(HttpServletRequest request,@RequestBody Address address) {
			try {
				addressService.saveAddress(address);
			} catch (IOException e) {
				return false;
			} catch (ServiceUnavailableException e) {
				return false;
			}
			return true;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list")
	public @ResponseBody List<Address> getAllAddress(HttpServletRequest request) throws ServiceUnavailableException {
		return addressService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/{boId}")
	public @ResponseBody Address findByAddressBoId(HttpServletRequest request,
		   @PathVariable String boId) throws ServiceUnavailableException {
		return addressService.findByBoId(boId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/city")
	public @ResponseBody List<Address> findByCity(HttpServletRequest request,
		   @RequestParam(value = "city") String city) throws ServiceUnavailableException {
		return addressService.findByCity(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/state")
	public @ResponseBody List<Address> findByState(HttpServletRequest request,
		   @RequestParam(value = "state") String state) throws ServiceUnavailableException {
		return addressService.findByState(state);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/find/country")
	public @ResponseBody List<Address> findByCountry(HttpServletRequest request,
		   @RequestParam(value = "country") String country) throws ServiceUnavailableException {
		return addressService.findByCountry(country);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public @ResponseBody Address findByLocationBoId(HttpServletRequest request,
			@RequestParam(value = "boId") String boId) throws ServiceUnavailableException {
		return addressService.findByBoId(boId);
	}

	@RequestMapping(method=RequestMethod.GET,value="/1")
	public @ResponseBody Address getAddress(HttpServletRequest request) throws ServiceUnavailableException {
		Address address = new Address();
		address.setBoId("ADR0030");
		return address;
	}
}
