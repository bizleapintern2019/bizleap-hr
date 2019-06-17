package com.bizleap.hr.service;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.Impl.CompanyServiceImpl;


public class CompanyServiceImplTest {

	CompanyService companyService=new CompanyServiceImpl();
	//@Test
	public void testSavingEmployee() {
		Company company=new Company("COM001");
		company.setAddress("ads");
		company.setEmail("bo@gmail.com");
		company.setCeo("CeoN");
		company.setPhone("0909");
		company.setCompanyName("boun");
		companyService.saveCompany(company);
		
	}
}