package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {
	CompanyService companyService = new CompanyServiceImpl();
	@Test
	public void testSavingEmployee() {
		Company company=new Company("Biz001");
		company.setCompanyName("Bizleap Technology");
		company.setAddress("Yangon");
		company.setPhone("09999898");
		company.setEmail("cc@gmail.com");
		company.setCeo("Robot");
		companyService.saveCompany(company);
	}
}
