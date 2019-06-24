package com.bizleap.hr.service;

import java.io.IOException;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface CompanyService {
    public void saveCompany(Company company) throws IOException, ServiceUnavailableException;
}