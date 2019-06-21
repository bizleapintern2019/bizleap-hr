package com.bizleap.hr.service.test;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyServiceJDBC;
import com.bizleap.hr.service.impl.CompanyServiceJDBCImpl;

public class CompanyServiceImplTest {
	CompanyServiceJDBC companyService = new CompanyServiceJDBCImpl();
	
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