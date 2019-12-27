package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//@author: Su Pyae Naing

@Entity
@Table(name = "location")
public class Location extends AbstractEntity {

	private String name;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<Department> departmentList;
	
	
	public Location() {
		super();
	}
	
	public Location(String boId) {
		super(boId);
	}

	public Location(String boId,List<Department> department, String name) {
		super.setBoId(boId);
		this.name = name;
		this.departmentList=department;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public void addDepartment(Department department) {
		
		if(getDepartmentList() == null) {
			departmentList = new ArrayList<Department>();
		}
		departmentList.add(department);
	}
	
	public static Location parseLocation(String dataLine) {
		
		Location location = new Location();
		String[] tokens = dataLine.split(";");
		location.setBoId(tokens[0]);
		location.setName(tokens[1]);
		String[] departmentList = tokens[2].split(",");
		
		if(location.getDepartmentList() == null) 
			location.setDepartmentList(new ArrayList<Department>());
		
		for(int i=0; i<departmentList.length; i++) 
			location.getDepartmentList().add(new Department(departmentList[i]));
		return location;
	}

	@Override
	public String toString() {
		return "Location: " + super.toString() + 
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Name: " + getName())
				.append("DepartmentList" + getDepartmentList());
	}
}