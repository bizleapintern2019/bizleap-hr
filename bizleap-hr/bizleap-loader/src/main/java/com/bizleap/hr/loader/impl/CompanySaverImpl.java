package com.bizleap.hr.loader.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.service.CompanyService;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

@Service
public class CompanySaverImpl implements CompanySaver {
	
	private static Logger logger = Logger.getLogger(CompanySaverImpl.class);
	
	private static Printer printer = new Printer(logger);   

	
	 @Autowired
	 private CompanyService companyService;	 
    
    
    List<Company> companyList;

    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Company: "+ getCompanyList().size());
        for(Company company:getCompanyList()) {
            companyService.saveCompany(company);
        }
        printer.line("Saving Completed");
    }

	public void setCompanyList(List<Company> companyList) {	
		
	}	

    public List<Company> getCompanyList() {
        return this.companyList;
    }
}