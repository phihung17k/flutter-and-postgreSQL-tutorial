/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_DAO;

import ASG1FA_BOOKINGHOTELDB_OBJECT.Rooms;
import UTIL.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RoomDAO {

    public List<Rooms> getRooms() throws SQLException, ClassNotFoundException {
        List<Rooms> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select RoomID\n"
                        + "      ,R_RoomTypeID\n"
                        + "      ,R_Status\n"
                        + "      ,R_Number from Rooms where r_status = 1 ";
                //3. tao statement
                stm = con.prepareStatement(sql);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    RoomTypeDAO dao = new RoomTypeDAO();
                    UsersDAO dao1 = new UsersDAO();
                    RentedRoomDAO dao2 = new RentedRoomDAO();
                    int roomnumber = rs.getInt("R_Number");
                    int roomid = rs.getInt("RoomID");

                    int typeid = rs.getInt("R_RoomTypeID");

                    String Name = dao1.getNameFromRoomCurrentDate(roomid);
                    if (Name.isEmpty()) {
                        Name = "No Customer for Today";
                    }
                    Rooms tmproom = new Rooms(roomnumber, dao.getRoomtypeNameAvailable(typeid), Name);
                    tmproom.setListrentedroom(dao2.getRooms(roomid));
                    list.add(tmproom);

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

    public boolean changeStatus(boolean status,int roomnumber) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "   update Rooms \n"
                        + "   set R_Status = ?\n"
                        + "   where r_status = 1 and  R_Number=?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, roomnumber);
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

}
