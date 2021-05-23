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
public class RoleDAO {
    public String getRoleName(int roleid) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select Rl_Name from Roles where rl_status = 1 and  RoleID =?";
                //3. tao statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, roleid);
                //4.query data
                rs = stm.executeQuery();
                //5.process
                if (rs.next()) {
                    String roomname = rs.getString("Rl_Name");
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
