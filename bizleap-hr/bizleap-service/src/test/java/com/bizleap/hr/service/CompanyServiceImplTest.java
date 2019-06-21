package com.bizleap.hr.service;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyServiceJDBC;
import com.bizleap.hr.service.impl.CompanyServiceJDBCImpl;

public class CompanyServiceImplTest {
	CompanyServiceJDBC companyService = new CompanyServiceJDBCImpl();
	
	@Ignore
	public void testSavingCompany(){
		Company company = new Company();
		company.setAddress("Thu Kha");
		company.setBoId("C009");
		company.setName("Bizleap Technology");
		company.setEmail("bizleap@gmail.com");
		company.setCeo("U Nyunt Than");
		company.setPhone("0912345678");
		companyService.saveCompany(company);
		System.out.println("Query Ok!");
	}
}
        