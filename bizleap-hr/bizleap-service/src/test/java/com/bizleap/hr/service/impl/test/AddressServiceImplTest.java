package com.bizleap.hr.service.impl.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;

public class AddressServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(AddressServiceImplTest.class);

	@Autowired
	AddressService addressService;

	@Test
	public void testSaveAddress() {
		
		Address address = new Address();
		address.setBoId("ADR001");
		address.setPermanentAddress("No.11");
		address.setContactAddress("zinziyar Str");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");

		try {
			addressService.saveAddress(address);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		
		logger.info(address);
	}
}