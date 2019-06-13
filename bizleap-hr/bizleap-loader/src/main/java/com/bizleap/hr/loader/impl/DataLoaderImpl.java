package com.bizleap.hr.loader.impl;


import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.FileLoader;

public class DataLoaderImpl implements DataLoader {
    DataManager dataManager;
    FileLoader fileLoader;

    public DataLoaderImpl(DataManager dataManager) {
        this.dataManager = dataManager;
        fileLoader = new FileLoaderImpl();
    }

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