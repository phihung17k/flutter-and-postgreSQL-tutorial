/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DbConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelManagementDb";
            con = DriverManager.getConnection(url, "sa", "123");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.out.println("Class DbConnection is not found. Terminating...");
            System.exit(-1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("SQL statement in DbConnection is not found. Terminating...");
            System.exit(-1);
        }
        return con;
    }
}
