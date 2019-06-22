package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CompanySaverImpl implements CompanySaver {
    private static Logger logger = Logger.getLogger(CompanySaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    CompanyService companyService;

    List<Company> companyList;

    @Override
    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Company: "+ getCompanyList().size());
        for(Company company:getCompanyList()) {
            companyService.saveCompany(company);
        }
        printer.line("Saving Completed");
    }

    @Override
    public void setCompanyList(List<Company> companyList) {
        this.companyList=companyList;
    }

    public List<Company> getCompanyList() {
        return this.companyList;
    }
}
