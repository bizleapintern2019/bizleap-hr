package com.bizleap.hr.service.impl;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.EmployeeService;
import com.bizleap.hr.service.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeServiceImplTest extends ServiceTest {

    private Logger logger = Logger.getLogger(EmployeeServiceImplTest.class);

    @Autowired
    EmployeeService employeeService;

    @Ignore
    @Test
    public void getAllEmployeeTest() {
        try {
            for (Employee employee: employeeService.getAll()) {
                logger.info(employee);
            }
        } catch (ServiceUnavailableException e) {
            logger.error(e);
        }
    }
}
