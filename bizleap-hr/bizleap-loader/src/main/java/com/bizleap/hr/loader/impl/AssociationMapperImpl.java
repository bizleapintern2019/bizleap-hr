package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import org.apache.log4j.Logger;

public class AssociationMapperImpl implements AssociationMapper {

    private Logger logger = Logger.getLogger(AssociationMapperImpl.class);

    DataManager dataManager;

    public AssociationMapperImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    private void addEmployeesToCompany(Company company) {
        for (Employee employee: dataManager.getEmployeeList()) {
            if (employee.getWorkForCompany().isSameBoId(company))
                company.getEmployeeList().add(employee);
        }
    }

    private void setUpCompanyAssociations() {
        for (Company company: dataManager.getCompanyList())
            addEmployeesToCompany(company);
    }


    private void addCompanyToEmployee(Employee employee) {
        for (Company company: dataManager.getCompanyList()) {
            if (company.isSameBoId(employee.getWorkForCompany())) {
                employee.setWorkForCompany(company);
                return; // to break loop when found // one to many
            }
        }
        handleLinkageError("Company in employee cannot be linked ", employee);
    }

    private void setUpEmployeeAssociations() {
        for (Employee employee: dataManager.getEmployeeList())
            addCompanyToEmployee(employee);
    }

    public void setUpAssociations() {
        logger.info("Calling setup associations");
//        setUpCompanyAssociations();
//        setUpEmployeeAssociations();
    }

    public void handleLinkageError(String message, Object source) {
        printDivider();
        System.out.println("|\tERROR");
        printDivider();
        System.out.println("Message: " + message);
        System.out.println("Source: " + source);
        printDivider();
        System.exit(0);
    }

    private void printDivider() {
        System.out.println("----------------------------------------");
    };

}
