package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AddressSaver {
	void savePass1() throws ServiceUnavailableException, IOException;
	void setAddressList(List<Address> addressList);
}