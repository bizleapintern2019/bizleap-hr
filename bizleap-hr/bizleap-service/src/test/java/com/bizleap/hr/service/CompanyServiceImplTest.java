package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {

	CompanyService companyService = new CompanyServiceImpl();

	@Test
	public void testSavingEmployee() {
		
		Company company = new Company();
		
		company.setBoId("COM001");
		company.setCompanyName("Technology");
		company.setAddress("Myanmar");
		company.setPhone("01548575");
		company.setEmail("technology@gmail.com");
		company.setCeo("U Kyaw Kyaw");
		
		companyService.saveCompany(company);
	}

}
