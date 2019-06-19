package com.bizleap.service.impl;

import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CompanyServiceImpl implements CompanyService {
	JDBCService jdbcService = new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {
		
			Connection connection=null;
			if(company == null)
				return;
			connection = jdbcService.getJDBCConnection();
			try {
				PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement("insert into company"+"(boId,name,address,phoneNumber,email,ceo)"+"values(?,?,?,?,?,?)");
				pStatement.setString(1, company.getBoId());
				pStatement.setString(2, company.getName());
				pStatement.setString(3, company.getAddress());
				pStatement.setString(4, company.getPhone());
				pStatement.setString(5, company.getEmail());
				pStatement.setString(6, company.getCeo());

				pStatement.executeUpdate();
				pStatement.close();
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}

}
