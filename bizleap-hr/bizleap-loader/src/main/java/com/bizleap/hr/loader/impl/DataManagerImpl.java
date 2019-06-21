package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataManagerImpl implements DataManager {

    @Autowired
    DataLoader dataLoader;

    List<Employee> employeeList;
    List<Company> companyList;

    public void load() {
        dataLoader.loadCompany();
        dataLoader.loadEmployee();
    }

    public List<Employee> getEmployeeList() {
        if (this.employeeList == null) {
            this.employeeList = new ArrayList<Employee>();
        }
        return this.employeeList;
    }

    public List<Company> getCompanyList() {
        if (this.companyList == null) {
            this.companyList = new ArrayList<Company>();
        }
        return this.companyList;
    }
}
