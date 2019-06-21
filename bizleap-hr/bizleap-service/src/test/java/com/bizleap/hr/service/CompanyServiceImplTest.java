package com.bizleap.hr.service;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.impl.CompanyServiceJDBCImpl;

public class CompanyServiceImplTest {

	CompanyServiceJDBC comployeeService=new CompanyServiceJDBCImpl();
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
