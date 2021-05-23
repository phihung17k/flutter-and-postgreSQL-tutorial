/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import utils.DbConnection;
import utils.StaticDetails;

/**
 *
 * @author Admin
 */
public class UserDAO {
    
    public User signIn(User user) {
        User result = null;
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        if (con != null) { //kết nối thành công
            try {
                String sql = "SELECT * "
                           + "FROM Users "
                           + "WHERE username = ? AND password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getPassword());
                
//                String encoded = StaticDetails.encryptPassword(user.getPassword());
//                pstm.setString(2, encoded);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    result = new User();
                    result.setId(rs.getInt("id"));
                    result.setUsername(rs.getString("username"));
                    result.setPassword(rs.getString("password"));
                    result.setFirstName(rs.getString("firstName"));
                    result.setLastName(rs.getString("lastName"));
                    result.setPhoneNumber(rs.getString("phoneNumber"));
                    result.setEmail(rs.getString("email"));
                    result.setAddress(rs.getString("address"));
                    result.setRoleId(rs.getInt("roleId"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error at signUp function in UserDAO");
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return result;
    }
    
    public boolean signUp(User user) {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        if (con != null) { //kết nối thành công
            try {
                String sql = "INSERT INTO Users "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                String encoded = StaticDetails.encryptPassword(user.getPassword());
                pstm.setString(1, user.getUsername());
                pstm.setString(2, encoded);
                pstm.setString(3, user.getFirstName());
                pstm.setString(4, user.getLastName());
                pstm.setString(5, user.getPhoneNumber());
                pstm.setString(6, user.getEmail());
                pstm.setString(7, user.getAddress());
                pstm.setInt(8, user.getRoleId());
                
                pstm.executeUpdate();
                result = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error at signIn function in UserDAO");
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return result;
    }
    
    public boolean isExisted(String username) {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        if (con != null) { //kết nối thành công
            try {
                String sql = StaticDetails.SqlCheckExist;
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                
                rs = pstm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error at isExisted function in UserDAO");
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return result;
    }
}
