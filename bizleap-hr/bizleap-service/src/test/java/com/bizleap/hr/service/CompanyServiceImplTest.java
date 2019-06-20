package com.bizleap.hr.service;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {
	CompanyService companyService = new CompanyServiceImpl();
	
	@Ignore
	public void testSavingCompany() {
		Company company = new Company();
		company.setBoId("C001");
		company.setCompanyName("Bizleap");
		company.setAddress("Thukha busStop");
		company.setEmail("bizleaptechnology@gmail.com");
		company.setPhone("01511522");
		company.setCeo("U Nyunt Than");
		companyService.saveCompany(company);
	}
}