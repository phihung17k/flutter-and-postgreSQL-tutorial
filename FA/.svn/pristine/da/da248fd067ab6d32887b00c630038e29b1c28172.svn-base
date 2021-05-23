package com.hungnp.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	public static Connection makeConnection() {
		String databaseName = "HotelManagementSystem_Hungnp";
		String user = "sa";
		String password = "phihung17k";
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName="+databaseName;
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
