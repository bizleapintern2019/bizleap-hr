package com.bizleap.hr.loader;

import java.util.List;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;

public interface DataLoader {
	List<Employee> loadEmployee() throws Exception;
	List<Department> loadDepartment() throws Exception;
	List<Location> loadLocation() throws Exception;
	List<Job> loadJob() throws Exception;
	List<Position> loadPosition() throws Exception;
	List<Address> loadAddress() throws Exception;
	int getIndex();
	void setIndex(int index);
}