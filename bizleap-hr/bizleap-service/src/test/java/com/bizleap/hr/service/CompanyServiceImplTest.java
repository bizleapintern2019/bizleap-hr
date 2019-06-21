package com.bizleap.hr.service;

import org.junit.Ignore;
import org.junit.Test;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.impl.CompanyServiceJDBCImpl;

public class CompanyServiceImplTest {
	CompanyServiceJDBC companyservice=new CompanyServiceJDBCImpl();
	//FileLoader fileLoader=new FileLoaderImpl();
	@Ignore
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
