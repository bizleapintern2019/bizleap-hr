package com.bizleap.commons.domain.entity;
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

public class AbstractEntity {
    private String boId;
    private String name;

    public AbstractEntity() {

    }

    public AbstractEntity(String boId) {
        this.boId = boId;
    }

    public String getBoId() {
        return boId;
    }

    public void setBoId(String boId) {
        this.boId = boId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSameBoId(AbstractEntity entity) {
        if (entity == null)
            return false;

        if (!entity.getClass().equals(getClass()))
            return false;

        if (entity.getBoId() == null)
            return false;

        return entity.getBoId().equals(getBoId());
    }
}
