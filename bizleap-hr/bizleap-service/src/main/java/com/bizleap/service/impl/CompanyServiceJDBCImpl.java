package com.bizleap.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.JDBCService;
import java.sql.PreparedStatement;;

@Ignore
public class CompanyServiceJDBCImpl implements CompanyServiceJDBC {
	@Autowired
	JDBCService jdbcService;
	Logger logger = Logger.getLogger(CompanyServiceJDBCImpl.class);
	@Override
	public void saveCompany(Company company) {
		Connection connection =null;
		if(company==null)
			return;
		connection= jdbcService.getJDBCconnection();
		if(connection==null) {
			logger.info("Connection Fail");
		}
		try {
			PreparedStatement ps = connection.prepareStatement("insert into company "+"(boid,name,address,phoneNo,email,ceo)"+" values (?,?,?,?,?,?)");
			ps.setString(1, company.getBoId());
			ps.setString(2, company.getCompanyName());
			ps.setString(3, company.getAddress());
			ps.setString(4, company.getPhone());
			ps.setString(5, company.getEmail());
			ps.setString(6, company.getCeo());
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
	}
}
		

	@Override
	public void openJBBCconnection() {
		// TODO Auto-generated method stub
		
	}

}
