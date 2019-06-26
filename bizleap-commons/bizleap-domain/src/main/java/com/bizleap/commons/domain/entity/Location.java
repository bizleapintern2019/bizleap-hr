package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "location")
public class Location extends AbstractEntity {

	private String locationName;
	private List<Department> departmentList;
	
	public Location() {
		super();
	}

	public Location(String boId, String locationName) {
		super.setBoId(boId);
		this.locationName = locationName;
	}
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public void addDepartment(Department department) {
		if(departmentList == null){
			departmentList = new ArrayList<Department>();
		}
		departmentList.add(department);
	}
	
	public static Location parseLocation(String dataLine) {
		Location location = new Location();
		String[] tokens = dataLine.split(";");
		location.setBoId(tokens[0]);
		location.setLocationName(tokens[1]);
		return location;
	}

	@Override
	public String toString() {
		return "Location: " + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Location Name: " + getLocationName());

	}
}