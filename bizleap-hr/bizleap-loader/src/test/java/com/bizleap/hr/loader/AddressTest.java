package com.bizleap.hr.loader;

import org.junit.Test;
import org.junit.Assert;
import com.bizleap.commons.domain.entity.Address;

public class AddressTest {

	@Test
	public void parseAddressTest() {
		Address.parseAddress("ADR001;No.11,zinziyar Str;No.79,zinziyar Str;Yangon;Yangon;Myanmar");
		Address address=Address.parseAddress("ADR001;No.11,zinziyar Str;No.79,zinziyar Str;Yangon;Yangon;Myanmar");
		Assert.assertEquals("ADR001",address.getBoId());
		Assert.assertEquals("No.11,zinziyar Str",address.getPermanentAddress());
		Assert.assertEquals("No.79,zinziyar Str",address.getContactAddress());
		Assert.assertEquals("Yangon",address.getCity());
		Assert.assertEquals("Yangon",address.getState());
		Assert.assertEquals("Myanmar",address.getCountry());
	}
}
