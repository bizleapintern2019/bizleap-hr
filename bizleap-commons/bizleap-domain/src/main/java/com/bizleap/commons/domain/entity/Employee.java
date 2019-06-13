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

import java.util.StringTokenizer;

public class Employee extends AbstractEntity {
    private Company workForCompany;

    public Company getWorkForCompany() {
        return workForCompany;
    }

    public void setWorkForCompany(Company workForCompany) {
        this.workForCompany = workForCompany;
    }

    public static Employee parse(String line) {
        Employee employee = new Employee();
        String[] tokens = line.split(";");
        employee.setBoId(tokens[0]);
        employee.setName(tokens[1]);
        employee.setWorkForCompany(new Company(tokens[2]));
        return employee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "workForCompanyBoId=" + workForCompany.getBoId() +
                ", boId='" + getBoId() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }
}
