package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.EmployeeSaver;
import com.bizleap.service.EmployeeService;

// @Author: Nyan Lin Htet
@Service
public class EmployeeSaverImpl implements EmployeeSaver {
	private static Logger logger = Logger.getLogger(EmployeeSaverImpl.class);

    @Autowired
    private EmployeeService employeeService;

    private List<Employee> employeeList;
    
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }
    
    public void savePass1() throws ServiceUnavailableException, IOException {
        logger.info("Saving Employee: " + getEmployeeList().size());
        for(Employee employee : getEmployeeList()) {
        	employeeService.saveEmployee(employee);
        }
        logger.info("Saving Completed");
    }
}