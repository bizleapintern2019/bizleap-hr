package com.bizleap.hr.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.impl.CompanyServiceJDBCImpl;

public class CompanyServiceImplTest {
	@Autowired
	CompanyServiceJDBC companyService; 
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
