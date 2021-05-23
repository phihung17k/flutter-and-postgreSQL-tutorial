/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_DAO;

import UTIL.DBHelper;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class UsersDAO {

    public int createAccount(String username, String password, String Name, String Phone) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "insert into Users values(?,?,?,?,?,?);";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setBytes(2, getSHA(password));
                stm.setString(3, Name);
                stm.setString(4, Phone);
                stm.setBoolean(5, true);
                stm.setBoolean(6, true);
                //4.query data
                rs = stm.executeUpdate();
                //5.process
                if (rs > 0) {
                    return 1;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return 0;
    }

    public int checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select U_RoleID,UserID from Users where u_status = 1 and  U_Username = ? and U_Password = ?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setBytes(2, getSHA(password));
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int role = rs.getInt("U_RoleID");
                    return role;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return 0;
    }

    public int getUserID(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select U_RoleID,UserID from Users where u_status = 1 and  U_Username = ?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int userid = rs.getInt("UserID");
                    return userid;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return 0;
    }

    public int getRole(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select U_RoleID,UserID from Users where u_status = 1 and U_Username = ?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int role = rs.getInt("U_RoleID");
                    return role;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return 0;
    }

    public String getUName(int userid) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select u_name from Users where u_status = 1 and UserID = ? ";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);

                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    String uname = rs.getString("u_name");
                    return uname;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return null;
    }

    private byte[] getSHA(String originalString) {
        byte[] hashbytes = null;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashbytes = digest.digest(
                    originalString.getBytes(StandardCharsets.UTF_8));
            String sha3Hex = bytesToHex(hashbytes);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashbytes;
    }

    private String bytesToHex(byte[] hashbytes) {
        StringBuilder hexString = new StringBuilder(2 * hashbytes.length);
        for (int i = 0; i < hashbytes.length; i++) {
            String hex = Integer.toHexString(0xff & hashbytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean setRole(String username, int role) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "update dbo.Users set Users.U_RoleID = ? where u_status = 1 and U_Username = ? ;";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setString(2, username);

                //4.query data
                rs = stm.executeUpdate();
                //5.process
                if (rs > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return false;
    }

    public HashMap<String, Integer> getUserWithRole() throws SQLException, ClassNotFoundException {
        HashMap<String, Integer> map = new HashMap<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select U_RoleID,U_Username from Users where  u_status = 1 ";
                //3. tao statement
                stm = con.prepareStatement(sql);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    int role = rs.getInt("U_RoleID");
                    String username = rs.getString("U_Username");
                    map.put(username, role);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return map;
    }

    public String getNameFromRoomCurrentDate(int roomid) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "select U_Name from Users where u_status = 1 and UserID = (select RR_UserID from RentedRoom where  (\n"
                        + "(RR_CheckinDate >= CONVERT(date, getdate())  and RR_CheckoutDate <= CONVERT(date, getdate()+1) ) or \n"
                        + "(RR_CheckinDate <= CONVERT(date, getdate())  and RR_CheckoutDate > CONVERT(date, getdate()+1) ) or  \n"
                        + "(RR_CheckinDate < CONVERT(date, getdate())  and RR_CheckoutDate >= CONVERT(date, getdate()+1) ) or\n"
                        + "(RR_CheckinDate < CONVERT(date, getdate())  and RR_CheckoutDate > CONVERT(date, getdate()+1) )\n"
                        + ") and RR_RoomID = ? )";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomid);

                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    String uname = rs.getString("u_name");
                    return uname;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return "";
    }

}
