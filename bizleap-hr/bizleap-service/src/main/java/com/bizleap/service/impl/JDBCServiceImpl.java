package com.bizleap.service.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;

public class JDBCServiceImpl implements JDBCService {

	@Override
	public Connection getJDBCConnection() {
		Connection connection = null;
		String dBName="internhr";
		String userName="root";
		String password="root";
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection =(Connection)DriverManager
						.getConnection("jdbc:mysql://localhost/"+dBName,userName,password);
				if(connection == null){
					System.out.println("Connection Null");
				}else{
					System.out.println("Connection is not null");
				}

			}catch(ClassNotFoundException e){
				System.out.println(e.getMessage());
			}

		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

}
