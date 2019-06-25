package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.CompanyDao;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<Company, String> implements CompanyDao {

    protected CompanyDaoImpl() {
        super(Company.class);
    }

    @Override
    public void save(Company company) throws ServiceUnavailableException {
        saveOrUpdate(company);
    }
}