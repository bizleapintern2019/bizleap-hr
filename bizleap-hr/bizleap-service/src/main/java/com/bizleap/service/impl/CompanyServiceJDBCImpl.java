package com.bizleap.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class CompanyServiceJDBCImpl implements CompanyServiceJDBC {
	@Autowired
	private JDBCService jdbcService;
	
	public void saveCompany(Company company) {
//		Connection connection= null;
//		if(company == null)
//			return;
//		connection = jdbcService.getJDBCConnection();
//		PreparedStatement pStatement;
//		try {
//			pStatement= (PreparedStatement) connection.prepareStatement("insert into company "+
//			"(BOID,CompanyName,CompanyAddress,CompnayPhoneNmumber,CompanyEmail,CEO)"+"values(?,?,?,?,?,?)");
//			pStatement.setString(1, company.getBoId());
//			pStatement.setString(2, company.getCompanyName());
//			pStatement.setString(3, company.getAddress());
//			pStatement.setString(4, company.getPhone());
//			pStatement.setString(5, company.getEmail());
//			pStatement.setString(6, company.getCeo());
//			
//			pStatement.executeUpdate();
//			pStatement.close();
//			connection.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}
