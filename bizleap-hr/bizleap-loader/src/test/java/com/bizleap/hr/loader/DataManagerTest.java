package com.bizleap.hr.loader;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataLoaderImpl;
import org.junit.Test;

import com.bizleap.hr.loader.impl.DataManagerImpl;

public class DataManagerTest {

    //static Company company;
    //static Employee employee;

    @Test
    public void dataTest() {
        DataManager dataManager = new DataManagerImpl();
        DataLoader dataLoader = new DataLoaderImpl(dataManager);
        AssociationMapper associationMapper = new AssociationMapperImpl(dataManager);

        dataManager.load(dataLoader);

        for (Company company : dataManager.getCompanyList()) {
            System.out.println(company);
        }

        for (Employee employee : dataManager.getEmployeeList()) {
            System.out.println(employee);
        }

        associationMapper.setUpAssociations();

        System.out.println(dataManager.getCompanyList().get(0));
    }
}
