package com.bizleap.hr.service.impl.test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;

import junit.framework.Assert;

public class AddressServiceImplTest extends ServiceTest {

	private Logger logger = Logger.getLogger(AddressServiceImplTest.class);

	@Autowired
	AddressService addressService;

	@Test
	public void testSaveAddress() {
		
		Address address = new Address();
		address.setBoId("ADR0030");
		address.setPermanentAddress("No.11,blah");
		address.setContactAddress("yadanar Str");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");

		try {
			addressService.saveAddress(address);
		} 
		catch (IOException e) {
			logger.error(e);
		} 
		catch (ServiceUnavailableException e) {
			logger.error(e);
		}
		
		logger.info(address);
	}
	
	@Test
	public void testGetAll() {
		try {
			List<Address> addressList = addressService.getAll();
			if(!CollectionUtils.isEmpty(addressList)){
				addressService.hibernateInitializedList(addressList);
				Assert.assertTrue(addressList.size()==26);
				Assert.assertEquals("ADR001", addressList.get(1).getBoId());
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByBoId() {
		try {
			List<Address> addressList = addressService.findByBoId("ADR006");
			if(!CollectionUtils.isEmpty(addressList)){
				addressService.hibernateInitializedList(addressList);
				Assert.assertTrue(addressList.size()==1);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByCity() {
		try {
			List<Address> addressList = addressService.findByCity("Bago");
			if(!CollectionUtils.isEmpty(addressList)){
				addressService.hibernateInitializedList(addressList);
				Assert.assertTrue(addressList.size()==1);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
	
	@Test
	public void testFindByState() {
		try {
			List<Address> addressList = addressService.findByState("Yangon");
			if(!CollectionUtils.isEmpty(addressList)){
				addressService.hibernateInitializedList(addressList);
				Assert.assertTrue(addressList.size()==1);
			}
		} catch (ServiceUnavailableException e) {
			logger.error("In Service Test: "+e);
		}
	}
}