package com.bizleap.hr.loader.impl;


import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderImpl implements DataLoader {
    @Autowired
    DataManager dataManager;

    @Autowired
    FileLoader fileLoader;

    public void loadEmployee() {
        fileLoader.start("employee.txt");

        Employee employee;

        while (fileLoader.hasNext()) {
            employee = Employee.parse(fileLoader.getLine());
            if (employee != null) {
                dataManager.getEmployeeList().add(employee);
            }
        }
    }

    public void loadCompany() {
        fileLoader.start("company.txt");

        Company company;

        while (fileLoader.hasNext()) {
            company = Company.parse(fileLoader.getLine());
            if (company != null) {
                dataManager.getCompanyList().add(company);
            }
        }
    }
}