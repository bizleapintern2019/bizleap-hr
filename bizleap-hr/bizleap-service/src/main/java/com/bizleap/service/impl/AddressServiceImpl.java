package com.bizleap.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.AddressDao;
import com.bizleap.service.AddressService;

//@Author: Thihan Hein
@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	@Transactional(readOnly = false)
	public void saveAddress(Address address) throws IOException, ServiceUnavailableException {
		addressDao.save(address);
	}
	
	@Transactional(readOnly = true)
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException {
		List<Address> addressList = addressDao.getAll("from Address");
		if(!CollectionUtils.isEmpty(addressList)) {
			hibernateInitializedList(addressList);	

			List<AbstractEntity> entityList = new ArrayList<AbstractEntity>();
			entityList.addAll(getAll());
			return entityList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Address> getAll() throws ServiceUnavailableException {
		List<Address> addressList = addressDao.getAll("from Address address");
		if(CollectionUtils.isNotEmpty(addressList)) {
			hibernateInitializedList(addressList);
			return addressList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Address findByBoId(String boId) throws ServiceUnavailableException {
		String query = "from Address address where address.boId=:dataInput";
		List<Address> addressList = addressDao.findByString(query, boId);
		if(CollectionUtils.isNotEmpty(addressList)) {
			hibernateInitializedAddress(addressList.get(0));
			return addressList.get(0);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Address> findByCity(String city) throws ServiceUnavailableException {

		String query = "from Address address where address.city=:dataInput";
		List<Address> addressList = addressDao.findByString(query, city);
		if(CollectionUtils.isNotEmpty(addressList)) {
			hibernateInitializedList(addressList);
			return addressList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Address> findByCountry(String country) throws ServiceUnavailableException {

		String query = "from Address address where address.country=:dataInput";
		List<Address> addressList = addressDao.findByString(query, country);
		if(CollectionUtils.isNotEmpty(addressList)) {
			hibernateInitializedList(addressList);
			return addressList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Address> findByState(String state) throws ServiceUnavailableException {

		String query = "from Address address where address.state=:dataInput";
		List<Address> addressList = addressDao.findByString(query, state);
		if(CollectionUtils.isNotEmpty(addressList)) {
			hibernateInitializedList(addressList);
			return addressList;
		}
		return null;
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