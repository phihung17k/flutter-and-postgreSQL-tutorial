/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_DAO;

import UTIL.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class RoomTypeDAO {
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

}
