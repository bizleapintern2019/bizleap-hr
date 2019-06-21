package com.bizleap.hr.service;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.Impl.CompanyServiceJDBCImpl;


public class CompanyServiceJDBCImplTest {

	CompanyServiceJDBC companyService=new CompanyServiceJDBCImpl();
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