package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {
	CompanyService companyService = new CompanyServiceImpl();
	@Test
	public void testSavingCompany(){
		Company company = new Company("COM001");
		company.setName("Bizleap Technology");
		company.setAddress("Myanmar");
		company.setPhone("0977638222");
		company.setEmail("biz@gmail.com");
		company.setCeo("UNT");
		
		companyService.saveCompany(company);
	}
}
