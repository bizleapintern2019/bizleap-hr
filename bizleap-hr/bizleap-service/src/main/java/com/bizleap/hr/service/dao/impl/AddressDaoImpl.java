package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.AddressDao;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<Address,String> implements AddressDao{
	protected AddressDaoImpl() {
		super(Address.class);
	}

	@Override
	public void save(Address address) throws ServiceUnavailableException {
		saveOrUpdate(address);
	}
}
