package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AddressService {
	
	void saveAddress(Address address) throws IOException, ServiceUnavailableException;
	List<Address> getAll() throws ServiceUnavailableException;
	List<Address> findByBoId(String boId) throws ServiceUnavailableException;
}