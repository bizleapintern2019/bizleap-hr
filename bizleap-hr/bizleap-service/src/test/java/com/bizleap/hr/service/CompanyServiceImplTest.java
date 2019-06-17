package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {
	CompanyService companyservice=new CompanyServiceImpl();
	@Test
	public void testSavingCompany(){
		Company company=new Company("COM001");
		company.setAddress("assddsf");
		company.setEmail("45345fdsfd@gmail.com");
		company.setCeo("fsfds");
		company.setPhone("535435");
		company.setName("BizLeap");
		companyservice.saveCompany(company);
		
	}
}
