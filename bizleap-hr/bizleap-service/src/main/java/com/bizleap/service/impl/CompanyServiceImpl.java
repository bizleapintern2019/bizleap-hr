//package com.bizleap.service.impl;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bizleap.commons.domain.entity.Company;
//import com.bizleap.commons.domain.exception.ServiceUnavailableException;
//import com.bizleap.hr.service.dao.CompanyDao;
//import com.bizleap.service.CompanyService;
//
//@Service
//public class CompanyServiceImpl implements CompanyService {
//	@Autowired
//	private CompanyDao companyDao;
//	
//	@Override
//	public void saveCompany(Company company) throws IOException, ServiceUnavailableException {
//		companyDao.save(company);
//	}
//}