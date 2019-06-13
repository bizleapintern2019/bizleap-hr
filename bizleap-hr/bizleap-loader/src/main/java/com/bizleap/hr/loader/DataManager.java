package com.bizleap.hr.loader;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;

import java.util.List;

public interface DataManager {
    void load(DataLoader dataLoader);
    List<Employee> getEmployeeList();
    List<Company> getCompanyList();
}
