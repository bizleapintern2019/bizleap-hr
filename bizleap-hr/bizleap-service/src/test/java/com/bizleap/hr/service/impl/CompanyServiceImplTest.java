package com.bizleap.hr.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.CompanyService;
import com.bizleap.hr.service.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyServiceImplTest extends ServiceTest {
    private Logger logger = Logger.getLogger(CompanyServiceImplTest.class);

    @Autowired
    CompanyService companyService;

    @Test
    public void getAllEmployeeTest() {
        try {
            for (Company company: companyService.getAllCompany()) {
                logger.info(company);
                logger.info(company.getEmployeeList());
            }
        } catch (ServiceUnavailableException e) {
            logger.error(e);
        }
    }
}
