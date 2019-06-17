package com.bizleap.service.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;

public class JDBCServiceImpl implements JDBCService {

	@Override
	public Connection getJDBCConnection() {
		// TODO Auto-generated method stub
		Connection connection=null;
		String dbName="InternHR";
		String userName="root";
		String password="root";
	 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+password);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

}
