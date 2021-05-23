/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_DAO;

import ASG1FA_BOOKINGHOTELDB_OBJECT.RentedRoom;
import UTIL.DBHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RentedRoomDAO {

    public int checkRoomAvailable(int roomtype, Date checkindate, Date checkoutdate, int quantity) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select RT_QUALITY - ? - (SELECT COUNT(RR_RoomID) from RentedRoom where rr_status = 1 and  RR_RoomID in (select Rooms.RoomID from Rooms where Rooms.R_RoomTypeID = ? ) and (\n"
                        + "(RR_CheckinDate >= ?  and RR_CheckoutDate <= ?) or \n"
                        + "(RR_CheckinDate <= ?  and RR_CheckoutDate > ? ) or  \n"
                        + "(RR_CheckinDate < ?  and RR_CheckoutDate >= ? ) or\n"
                        + "(RR_CheckinDate < ?  and RR_CheckoutDate > ? )\n"
                        + ")\n"
                        + "  ) as ctQuality from RoomTypes where RoomTypeID =?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, roomtype);
                stm.setDate(3, checkindate);
                stm.setDate(4, checkoutdate);
                stm.setDate(5, checkindate);
                stm.setDate(6, checkoutdate);
                stm.setDate(7, checkindate);
                stm.setDate(8, checkoutdate);
                stm.setDate(9, checkindate);
                stm.setDate(10, checkoutdate);
                stm.setInt(11, roomtype);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int quality = rs.getInt("ctQuality");
                    return quality + quantity;
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

    public int getRoomIDAvailable(int roomtype, Date checkindate, Date checkoutdate) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select Roomid from Rooms where r_status = 1 and  R_RoomTypeID=? and Roomid not in (Select RR_RoomID from RentedRoom where "
                        
                        + "((RR_CheckinDate >= ? and RR_CheckoutDate <=?) or \n"
                        + "(RR_CheckinDate <= ?  and RR_CheckoutDate > ?) or  \n"
                        + "(RR_CheckinDate < ? and RR_CheckoutDate >= ? ) or\n"
                        + "(RR_CheckinDate <   ? and RR_CheckoutDate > ? ))) ";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomtype);
                stm.setDate(2, checkindate);
                stm.setDate(3, checkoutdate);
                stm.setDate(4, checkindate);
                stm.setDate(5, checkoutdate);
                stm.setDate(6, checkindate);
                stm.setDate(7, checkoutdate);
                stm.setDate(8, checkindate);
                stm.setDate(9, checkoutdate);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int roomid = rs.getInt("Roomid");
                    return roomid;
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

    public int getRoomnumberAvailable(int roomid) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select R_Number from Rooms where r_status = 1 and  Roomid =?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomid);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int roomnumber = rs.getInt("R_Number");
                    return roomnumber;
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

    public String getRoomtypeNameAvailable(int roomtype) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select RT_Name from RoomTypes where rt_status = 1 and  RoomTypeID =?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomtype);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    String roomname = rs.getString("RT_Name");
                    return roomname;
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
        return null;
    }

    public int getRoompriceAvailable(int roomtype) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select RT_Price from RoomTypes where rt_status = 1 and  RoomTypeID =?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomtype);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    int RT_Price = rs.getInt("RT_Price");
                    return RT_Price;
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

    public boolean addRentedRoom(int roomid, int userid, Date checkindate, Date checkoutdate) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "insert into RentedRoom values(?,?,?,?,?)";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomid);
                stm.setInt(2, userid);
                stm.setDate(3, checkindate);
                stm.setDate(4, checkoutdate);
                stm.setBoolean(5, true);
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

     public List<RentedRoom> getRooms(int Roomid) throws SQLException, ClassNotFoundException {
        List<RentedRoom> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select RR_RoomID\n"
                        + "      ,RR_UserID\n"
                        + "      ,RR_CheckinDate\n"
                        + "      ,RR_CheckoutDate from RentedRoom where rr_status = 1 and  RR_RoomID = ?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, Roomid);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    int userid = rs.getInt("RR_UserID");
                    int roomid = rs.getInt("RR_RoomID");
                    Date checkindate = rs.getDate("RR_CheckinDate");
                    Date checkoutdate = rs.getDate("RR_CheckoutDate");
                    RentedRoom rr = new RentedRoom(roomid, userid, checkindate, checkoutdate);
                    list.add(rr);

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
        return list;
    }

}
