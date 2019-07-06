package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.AddressDao;
import com.bizleap.service.AddressService;

//@Author: Thihan Hein
//@Service("addressService1")
@Service
//@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	public void saveAddress(Address address) throws IOException, ServiceUnavailableException {
		addressDao.save(address);
	}

	public List<Address> getAll() throws ServiceUnavailableException {
		
		List<Address> addressList = addressDao.getAll("from Address address");
		return addressList;
	}
	
	public List<Address> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "select address from Address address where address.boId=:dataInput";
		List<Address> addressList = addressDao.findByString(query, boId);
		return addressList;
	}
	
	public List<Address> findByCity(String city) throws ServiceUnavailableException {

		String query = "select address from Address address where address.city=:dataInput";
		List<Address> addressList = addressDao.findByString(query, city);
		return addressList;
	}
	
	public List<Address> findByCountry(String country) throws ServiceUnavailableException {

		String query = "select address from Address address where address.country=:dataInput";
		List<Address> addressList = addressDao.findByString(query, country);
		return addressList;
	}
	
	public List<Address> findByState(String state) throws ServiceUnavailableException {

		String query = "select address from Address address where address.state=:dataInput";
		List<Address> addressList = addressDao.findByString(query, state);
		return addressList;
	}
	
	public void hibernateInitializedAddress(Address address) {
		Hibernate.initialize(address);
	}
	
	public void hibernateInitializedList(List<Address> addressList) {
		for(Address address : addressList) {
			hibernateInitializedAddress(address);	
		}
	}
}