/*
Assignment 4
There are three persons and three companies (you can add more):
Alice Kim -- works for Apple
Bob Gilman -- works for IBM
John Mark -- works for Adobe

1) Write a java program that will read the above employees' first name, last name, age, title, salary,
 email, phone ( add more fields to above people) from a file as well as to read the company names, address,
  phone, email, CEO (make up some one for each company) from a second file and print out the company then 
  it's employee(s) for all companies.
2) Find out what commonality do the two entity classes have and reimplement it
 by using the inheritance features of Java. 
*/
package com.bizleap.commons.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends AbstractEntity {
    private String ceo;

    @OneToMany( mappedBy="workForCompany", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    private List<Employee> employeeList;

    public Company() {

    }

    public Company(String boId) {
        super(boId);
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public List<Employee> getEmployeeList() {
        if(employeeList == null)
            employeeList = new ArrayList<Employee>();
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public static Company parse(String line) {
        Company company = new Company();
        String[] tokens = line.split(";");
        company.setBoId(tokens[0]);
        company.setName(tokens[1]);
        company.setCeo(tokens[2]);
        return company;
    }

    @Override
    public String toString() {
        return "Company{" +
                "employeeList=" + employeeList +
                ", boId='" + getBoId() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }
}
