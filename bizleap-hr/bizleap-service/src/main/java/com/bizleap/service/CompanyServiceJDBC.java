package com.bizleap.service;

import com.bizleap.commons.domain.entity.Company;

public interface CompanyServiceJDBC {
	public void openJBBCconnection();
	public void saveCompany(Company company);
}
