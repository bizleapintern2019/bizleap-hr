package com.bizleap.hr.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.dao.CompanyDao;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    
	private Logger logger = Logger.getLogger(CompanyServiceImpl.class);
    @Override
    public void saveCompany(Company company) throws IOException, ServiceUnavailableException {
    	logger.info("Saving data: " + company);
        companyDao.save(company);
    }
}