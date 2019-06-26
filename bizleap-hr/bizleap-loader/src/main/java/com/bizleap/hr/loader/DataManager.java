package com.bizleap.hr.loader;

import java.util.List;
import java.util.Map;

<<<<<<< HEAD
=======
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Company;
>>>>>>> e002ca1d7aca3d8b0c604b94b6ff0b3501bd1f39
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
	List<Address> getAddressList();
	Map<Integer,ErrorCollection> getErrorMap();
	void load();
}
