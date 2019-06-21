package com.bizleap.hr.loader;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.impl.AssociationMapperImpl;
import com.bizleap.hr.loader.impl.DataLoaderImpl;
import com.bizleap.hr.service.impl.test.ServiceTest;
import org.junit.Test;

import com.bizleap.hr.loader.impl.DataManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class DataManagerTest extends ServiceTest {

    //static Company company;
    //static Employee employee;
    @Autowired
    DataManager dataManager;

    @Autowired
    AssociationMapper associationMapper;

    @Test
    public void dataTest() {
        dataManager.load();

    }

}
