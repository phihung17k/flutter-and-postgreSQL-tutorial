/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookingDetail;
import dto.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import utils.DbConnection;
import utils.StaticDetails;

/**
 *
 * @author Admin
 */
public class RoomDAO {
    
    private static void closeConnection(Connection con, PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.err.println("Error while closing connection!");
        }
    }
    
    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> result = new ArrayList<>();
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                String sql = StaticDetails.SQL_GET_ROOMS;
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    
                    Room tmp = new Room(name, price);
                    tmp.setId(id);
                    result.add(tmp);
                }
            } catch (Exception e) {
                System.err.println("Error occured at getAllRooms in RoomDAO");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
    
    public HashMap<Integer, Room> getAllRooms2() {
        HashMap<Integer, Room> result = new HashMap<Integer, Room>();
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                String sql = StaticDetails.SQL_GET_ROOMS;
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    
                    Room tmp = new Room(name, price);
                    tmp.setId(id);
                    result.put(id, tmp);
                }
            } catch (Exception e) {
                System.err.println("Error occured at getAllRooms2 in RoomDAO");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
    
    public HashMap<Integer, Room> getAvailableRooms() {
        HashMap<Integer, Room> result = new HashMap();
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                String sql = StaticDetails.SQL_GET_AVAIL_ROOMS;
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    
                    Room tmp = new Room(name, price);
                    tmp.setId(id);
                    result.put(id, tmp);
                }
            } catch (Exception e) {
                System.err.println("Error occured at getAvailableRooms in RoomDAO");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
    
    public Room getRoom(int id) {
        Room result = null;
        Connection con = DbConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        if (con != null) {
            try {
                sql = StaticDetails.SQL_GET_ROOM;
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, id);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    Integer idFromDb = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    
                    result = new Room(name, price);
                    result.setId(idFromDb);
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Error at getRoom in RoomDAO");
                System.err.println(e.getMessage());
            } finally {
                closeConnection(con, pstm, rs);
            }
        }
        return result;
    }
}
