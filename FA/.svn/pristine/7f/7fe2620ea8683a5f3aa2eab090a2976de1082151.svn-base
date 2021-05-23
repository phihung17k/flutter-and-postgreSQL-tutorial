/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class StaticDetails {
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.encode(hashed);
        return encoded;
    }
    
    public static java.sql.Date convertDate(Date pickedDate) {
        if (pickedDate == null) {
            return null;
        }
        java.sql.Date sqlDate = new java.sql.Date(pickedDate.getTime());
        return sqlDate;
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
    
    
    public final static String SqlCheckExist =
            "SELECT username "
            + "FROM Users "
            + "WHERE username = ?";
    
    public final static String SQL_GET_ROOMS =
            "SELECT * "
            + "FROM Rooms";
    
    public final static String SQL_GET_AVAIL_ROOMS =
            "SELECT r.* "
            + "FROM Rooms r, BookingDetail bd "
            + "WHERE bd.isBooked = 0";
    
    public final static String SQL_INSERT_BD = 
            "INSERT INTO BookingDetail "
            + "VALUES(?, ?, ?, ?, ?)";
    
    public final static String SQL_GET_ROOM =
            "SELECT * "
            + "FROM Rooms "
            + "WHERE id = ?";
    
    public final static String SQL_GET_DETAIL_ROOM =
            "SELECT * "
            + "FROM BookingDetail "
            + "WHERE roomId = ?";
}
