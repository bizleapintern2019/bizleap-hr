package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Address;

public class AddressTest {

	@Test
	public void parseAddressTest() {
		Address.parseAddress("ADR001;No.11,zinziyar Str;No.79,zinziyar Str;Yangon;Yangon;Myanmar");
	}
}
