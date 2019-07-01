package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.AddressDao;
import com.bizleap.service.AddressService;

//@Author: Thihan Hein
@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	public void saveAddress(Address address) throws IOException, ServiceUnavailableException {
		addressDao.save(address);
	}

	public List<Address> getAll() throws ServiceUnavailableException {
		
		List<Address> addressList = addressDao.getAll("From Address address");
		return addressList;
	}
	
	public List<Address> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "select address from Address address where address.boId=:dataInput";
		List<Address> addressList = addressDao.findByString(query, boId);
		return addressList;
	}
}