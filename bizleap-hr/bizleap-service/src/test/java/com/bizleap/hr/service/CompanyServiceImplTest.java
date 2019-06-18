package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.impl.CompanyServiceImpl;

public class CompanyServiceImplTest {

	CompanyService comployeeService=new CompanyServiceImpl();
//	@Test
	public void testSavingComployee() {
		Company company = new Company("COM002");
		company.setAddress("asd");
		company.setEmail("ASd");
		company.setPhone("Asd");
		company.setCeo("asd");
		company.setName("Shine");

		//comployeeService.saveCompany(company);
	}
}
