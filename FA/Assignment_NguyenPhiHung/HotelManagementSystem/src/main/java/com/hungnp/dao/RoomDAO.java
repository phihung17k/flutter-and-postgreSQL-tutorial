package com.hungnp.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hungnp.dto.Room;
import com.hungnp.utils.MyConnection;

public class RoomDAO implements Serializable{

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
	
	public List<Room> getAllRooms(){
		List<Room> listRoom = null;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select r.RoomNo, t.RoomTypeName, r.Price, r.Unit, r.IsEmpty "
						+ "from Room r inner join RoomType t on r.RoomTypeID=t.RoomTypeID ";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					if(listRoom==null) {
						listRoom = new ArrayList<Room>();
					}
					int roomNo = resultSet.getInt("RoomNo");
					String roomType = resultSet.getString("RoomTypeName");
					double price = resultSet.getDouble("Price");
					String unit = resultSet.getString("Unit");
					boolean isEmpty = resultSet.getBoolean("IsEmpty");
					Room room = new Room(roomNo, roomType, price, unit, isEmpty);
					listRoom.add(room);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listRoom;
	}
	
	public List<Room> searchRoomNo(String searchValue){
		List<Room> listRoom = null;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select r.RoomNo, t.RoomTypeName, r.Price, r.Unit, r.IsEmpty "
						+ "from Room r inner join RoomType t on r.RoomTypeID=t.RoomTypeID "
						+ "where r.RoomNo like ? ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, "%"+searchValue+"%");
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					if(listRoom==null) {
						listRoom = new ArrayList<Room>();
					}
					int roomNo = resultSet.getInt("RoomNo");
					String roomType = resultSet.getString("RoomTypeName");
					double price = resultSet.getDouble("Price");
					String unit = resultSet.getString("Unit");
					boolean isEmpty = resultSet.getBoolean("IsEmpty");
					Room room = new Room(roomNo, roomType, price, unit, isEmpty);
					listRoom.add(room);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listRoom;
	}
	
	public int countRoom(){
		int numOfRoom = -1;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select count(RoomNo) as countRoom "
						+ "from Room ";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					numOfRoom = resultSet.getInt("countRoom");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return numOfRoom;
	}
	
	public Room getRoom(int roomNo) {
		Room room = null;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select t.RoomTypeName, r.Price, r.Unit, r.IsEmpty "
						+ "from Room r inner join RoomType t on r.RoomTypeID=t.RoomTypeID "
						+ "where r.RoomNo = ? ";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, roomNo);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					String roomType = resultSet.getString("RoomTypeName");
					double price = resultSet.getDouble("Price");
					String unit = resultSet.getString("Unit");
					boolean isEmpty = resultSet.getBoolean("IsEmpty");
					room = new Room(roomNo, roomType, price, unit, isEmpty);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return room;
	}
	
	public boolean updateStatusRoom(int roomNo, String status) {
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "update Room "
						+ "set IsEmpty = ? "
						+ "where RoomNo = ? ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, status.equals("y")? "true":"false");
				statement.setInt(2, roomNo);
				int row = statement.executeUpdate();
				if(row>0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return false;
	}
	
	public List<Room> getRoomsForCustomer(String bookingDate, String checkoutDate){
		List<Room> listRoom = null;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select r.RoomNo, t.RoomTypeName, r.Price, r.Unit "
						+ "from Room r inner join RoomType t on r.RoomTypeID=t.RoomTypeID "
						+ "where RoomNo not in (select r.RoomNo "
								+ "from Room r inner join Booking b on r.RoomNo=b.RoomNo "
								+ "where  (? < b.BookingDate and ? >= b.BookingDate) "
								+ "or (? > b.CheckoutDate and ? <= b.CheckoutDate) "
								+ "or (? >= b.BookingDate and ? <= b.CheckoutDate)) ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, bookingDate);
				statement.setString(2, checkoutDate);
				statement.setString(3, checkoutDate);
				statement.setString(4, bookingDate);
				statement.setString(5, bookingDate);
				statement.setString(6, checkoutDate);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					if(listRoom==null) {
						listRoom = new ArrayList<Room>();
					}
					int roomNo = resultSet.getInt("RoomNo");
					String roomType = resultSet.getString("RoomTypeName");
					double price = resultSet.getDouble("Price");
					String unit = resultSet.getString("Unit");
					Room room = new Room(roomNo, roomType, price, unit);
					listRoom.add(room);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listRoom;
	}
}
