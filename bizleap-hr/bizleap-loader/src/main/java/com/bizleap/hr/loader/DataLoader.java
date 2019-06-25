package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;

public interface DataLoader {
	public List<Employee> loadEmployee() throws Exception;
	public List<Department> loadDepartment() throws Exception;
	public List<Location> loadLocation() throws Exception;
	public List<Job> loadJob() throws Exception;
	public List<Position> loadPosition() throws Exception;
	public List<Address> loadAddress() throws Exception;
	public int getIndex();
	public void setIndex(int index);
}
