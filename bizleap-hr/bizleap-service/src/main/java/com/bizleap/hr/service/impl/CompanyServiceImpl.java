package com.bizleap.hr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.dao.CompanyDao;

@Service
//@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public void saveCompany(Company company) throws IOException, ServiceUnavailableException {
        companyDao.save(company);
    }
}
