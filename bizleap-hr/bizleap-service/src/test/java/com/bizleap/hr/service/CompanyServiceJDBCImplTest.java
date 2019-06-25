package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {

	CompanyService companyService = new CompanyServiceImpl();
	@Test
	public void testSavingCompany() {
		Company company = new Company();
		company.setAddress("9 mile");
		company.setBoId("C003");
		company.setCeo("U Blah");
		company.setCompanyName("KBZ");
		company.setEmail("kbz@gmail.com");
		company.setPhone("098884443");
		companyService.saveCompany(company);
		System.out.println("Query ok!");
	}
}
