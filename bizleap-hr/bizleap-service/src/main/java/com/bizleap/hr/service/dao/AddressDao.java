package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AddressDao extends AbstractDao<Address,String>{
	void save(Address address)throws ServiceUnavailableException;
}
