package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.DepartmentSaver;
import com.bizleap.service.DepartmentService;

@Service
public class DepartmentSaverImpl implements DepartmentSaver{
	
	private static Logger logger = Logger.getLogger(DepartmentSaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    private DepartmentService departmentService;

    private List<Department> departmentList;

    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Department: "+ getDepartmentList().size());
        for(Department department:getDepartmentList()) {
        	departmentService.saveDepartment(department);
        }
        printer.line("Saving Completed");
    }

    public void setDepartmentList( List<Department> departmentList) {
        this.departmentList=departmentList;
    }

    public List<Department> getDepartmentList() {
        return this.departmentList;
    }
}
