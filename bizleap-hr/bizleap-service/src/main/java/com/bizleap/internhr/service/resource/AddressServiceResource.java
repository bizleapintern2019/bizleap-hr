package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AddressServiceResource {
	
	boolean createAddress(HttpServletRequest request,@RequestBody Address address);
	List<Address> getAllAddress(HttpServletRequest request) throws ServiceUnavailableException;
	Address findByAddressBoId(HttpServletRequest request,String boId) throws ServiceUnavailableException;
	List<Address> findByCity(HttpServletRequest request, String city) throws ServiceUnavailableException;
	List<Address> findByState(HttpServletRequest request, String state) throws ServiceUnavailableException;
	List<Address> findByCountry(HttpServletRequest request, String country) throws ServiceUnavailableException;
	Address getAddress(HttpServletRequest request) throws ServiceUnavailableException;
}
