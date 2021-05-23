/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBHelper {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException
    {
       
            //1load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://localhost:1433;databaseName=ASG1FA_BOOKINGHOTELDB";
            Connection cnn = DriverManager.getConnection(url,"sa","123456");
            
            return cnn;
        
    }
}
