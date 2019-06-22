package com.bizleap.hr.service;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;


public interface CompanyService {
    void saveCompany(Company company) throws IOException, ServiceUnavailableException;
    List<Company> getAllCompany() throws ServiceUnavailableException;
}
