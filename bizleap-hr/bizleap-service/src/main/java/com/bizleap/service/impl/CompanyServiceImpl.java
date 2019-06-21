package com.bizleap.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.service.CompanyService;
import com.bizleap.service.dao.CompanyDao;

public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public void saveCompany(Company company) throws IOException, ServiceUnavailableException {
		 companyDao.save(company);
	}

	
	
}
