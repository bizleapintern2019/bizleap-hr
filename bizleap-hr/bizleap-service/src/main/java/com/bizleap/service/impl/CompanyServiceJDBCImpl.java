package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyServiceJDBC;
import com.bizleap.service.JDBCService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceJDBCImpl implements CompanyServiceJDBC {

	private  JDBCService jdbcService =new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {	 
			if (company == null)
				return;
			Connection connection = null;
			PreparedStatement ps;
			try {
				connection = jdbcService.getJDBCConnection();
				ps =  connection.prepareStatement(
						"insert into company" + "(boId,name,address,phone,email,ceo)"
								+ " values(?,?,?,?,?,?)");

				ps.setString(1, company.getBoId());
				ps.setString(2, company.getName());
				ps.setString(3, company.getAddress());
				ps.setString(4, company.getPhone());
				ps.setString(5, company.getEmail());
				ps.setString(6, company.getCeo());

				ps.executeUpdate();
				ps.close();	
				connection.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
	}

	 
}
