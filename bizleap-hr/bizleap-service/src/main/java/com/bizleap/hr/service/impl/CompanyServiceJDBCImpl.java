package com.bizleap.hr.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyServiceJDBC;
import com.bizleap.hr.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class CompanyServiceJDBCImpl implements CompanyServiceJDBC {
	@Autowired
	JDBCService jdbcService;
	
	public void saveCompany(Company company) {
		Connection connection= null;
		if(company == null)
			return;
		connection = jdbcService.getJDBCConnection();
		PreparedStatement pStatement;
		try {
			pStatement= (PreparedStatement) connection.prepareStatement("insert into company "+
			"(boId,name,address,phoneNumber,email,ceo)"+"values(?,?,?,?,?,?)");
			pStatement.setString(1, company.getBoId());
			pStatement.setString(2, company.getCompanyName());
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
