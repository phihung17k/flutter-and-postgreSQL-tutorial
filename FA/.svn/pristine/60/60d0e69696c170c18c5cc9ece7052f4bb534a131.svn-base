/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookingDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import utils.DbConnection;
import utils.StaticDetails;

/**
 *
 * @author Admin
 */
public class BookingDetailDAO {
    
    private static void closeConnection(Connection con, PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.err.println("Error while closing connection!");
        }
    }
    
    public boolean insert(BookingDetail bd) {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        if (con != null) {
            try {
                sql = StaticDetails.SQL_INSERT_BD;
                //userid, roomid, booking, expire, booked
                //  1,       2,      3,       4,      5
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, bd.getUserId());
                pstm.setInt(2, bd.getRoomId());
                pstm.setBoolean(5, true);
                java.sql.Date booking = StaticDetails.convertDate(bd.getBookingDate());
                java.sql.Date expire = StaticDetails.convertDate(bd.getExpireDate());
                if (booking != null && expire != null) {
                    pstm.setDate(3, booking);
                    pstm.setDate(4, expire);

                    pstm.executeUpdate();
                    result = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "You have already booked that room in those days!\nPlease book other room!");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
    
    public ArrayList<BookingDetail> getDetails(int roomId) {
        ArrayList<BookingDetail> result = new ArrayList<>();
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        if (con != null) {
            try {
                sql = StaticDetails.SQL_GET_DETAIL_ROOM;
                //roomid
                //  1
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, roomId);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    result.add(new BookingDetail(
                            rs.getInt("userId"), 
                            roomId, 
                            new Date(rs.getDate("bookingDate").getTime()), 
                            new Date(rs.getDate("expireDate").getTime()), 
                            rs.getBoolean("isBooked")));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "You have already booked that room in those days!\nPlease book other room!");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
}
