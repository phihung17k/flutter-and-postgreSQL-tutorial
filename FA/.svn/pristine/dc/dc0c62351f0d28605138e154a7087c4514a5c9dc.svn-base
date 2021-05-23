package com.hungnp.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hungnp.utils.MyConnection;

public class BookingDAO implements Serializable{
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private void closeConnection() {
		try {
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createBookingRoom(String bookingDate, String checkoutDate, String accountId, int roomNumber) {
		try {
			connection = MyConnection.makeConnection();
			if(connection!=null) {
				String sql = "insert Booking(BookingDate, CheckoutDate, AccountID, RoomNo) "
						+ "values(?, ?, ?, ?) ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, bookingDate);
				statement.setString(2, checkoutDate);
				statement.setString(3, accountId);
				statement.setInt(4, roomNumber);
				int row = statement.executeUpdate();
				if(row>0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return false;
	}
}
