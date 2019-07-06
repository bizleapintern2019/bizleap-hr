package com.bizleap.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AddressService {
	
	void saveAddress(Address address) throws IOException, ServiceUnavailableException;
	List<Address> getAll() throws ServiceUnavailableException;
	List<Address> findByBoId(String boId) throws ServiceUnavailableException;
	List<Address> findByCity(String city) throws ServiceUnavailableException;
	List<Address> findByCountry(String country) throws ServiceUnavailableException;
	List<Address> findByState(String state) throws ServiceUnavailableException;
	void hibernateInitializedAddress(Address address);
	void hibernateInitializedList(List<Address> addressList);
}