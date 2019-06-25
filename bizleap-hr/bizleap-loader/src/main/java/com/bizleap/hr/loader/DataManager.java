package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;

public interface DataManager {
	void loadData();
	List<Employee> getEmployeeList();
	List<Department> getDepartmentList(); 
	List<Job> getJobList();
	List<Location> getLocationList();
	List<Position> getPositionList();
	Map<Integer,ErrorCollection> getErrorMap();
	void load();
}
