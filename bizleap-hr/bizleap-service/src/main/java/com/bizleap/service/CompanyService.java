package com.bizleap.service;


import java.io.IOException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface CompanyService {
	void saveCompany(Company company) throws IOException, ServiceUnavailableException;
}
