package com.bizleap.hr.service.impl;


import com.bizleap.hr.service.EmployeeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.dao.CompanyDao;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    EmployeeService employeeService;

    @Override
    public void saveCompany(Company company) throws IOException, ServiceUnavailableException {
        companyDao.save(company);
    }

    @Override
    public List<Company> getAllCompany() throws ServiceUnavailableException {
        List<Company> companyList = companyDao.getAll("From Company company");
        hibernateInitializeCompanyList(companyList);
        return companyList;
    }

    public void hibernateInitializeCompanyList(List<Company> companyList) throws ServiceUnavailableException {
        for (Company company: companyList)
            hibernateInitializeCompany(company);
    }

    private void hibernateInitializeCompany(Company company) throws ServiceUnavailableException {
        employeeService.hibernateInitializeEmployeeList(company.getEmployeeList());
    }
}
