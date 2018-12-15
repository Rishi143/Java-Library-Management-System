package com.library.dao;

import java.sql.*;

public class DbUtil {
	private Connection libraryConnection=null;
	
	private PreparedStatement libraryPst=null;
	
	public Connection getConnection() 
	{
		try {
			Class.forName(DbConstants.driverClassName);
			libraryConnection=DriverManager.getConnection(DbConstants.url,DbConstants.username,DbConstants.password);
		} catch (Exception e) {
			System.out.println("Unnable to get DbConnection..");
			e.printStackTrace();
		}
		return libraryConnection;
	}
	
	public PreparedStatement getPreparedStatement(String sqlCommand)
	{
		try {
			libraryPst=getConnection().prepareStatement(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to create Prepared Statement");
			e.printStackTrace();
		}
		return libraryPst;

	}
}
