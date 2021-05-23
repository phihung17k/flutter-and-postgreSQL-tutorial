package com.hungnp.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hungnp.dto.Account;
import com.hungnp.dto.Room;
import com.hungnp.utils.MyConnection;

public class AccountDAO implements Serializable{
	
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
	
	public boolean loginAccount(String accountId, String password) {
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select AccountID " + "from Account " + "where AccountID=? and Password=? ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, accountId);
				statement.setString(2, password);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
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
	
	public boolean checkExistedAccount(String accountId) {
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select AccountID from Account where AccountID=? ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, accountId);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
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
	
	public boolean registerAccount(String accountId, String password) {
		try {
			connection = MyConnection.makeConnection();
			if(connection!=null) {
				String sql = "insert into Account(AccountID, Password) "
								+ "values(?, ?) ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, accountId);
				statement.setString(2, password);
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
	
	public String getRole (String accountId) {
		String role = "";
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select r.AccountRoleName "
								+ "from Account a inner join AccountRole r on a.AccountRoleID=r.AccountRoleID "
								+ "where a.AccountID=? ";
				statement = connection.prepareStatement(sql);
				statement.setString(1, accountId);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					role = resultSet.getString("AccountRoleName");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return role;
	}
	
	public boolean setRole(String accountId, int chosenRole) {
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "update Account "
							+ "set AccountRoleID = (select AccountRoleID "
											+ "from AccountRole "
											+ "where AccountRoleName = ?) "
							+ "where AccountID = ? ";
				statement = connection.prepareStatement(sql);
				if(chosenRole == 1) {
					statement.setString(1, "Admin");
				}else if(chosenRole == 2) {
					statement.setString(1, "Clerk");
				}else {
					statement.setString(1, "Customer");
				}
				statement.setString(2, accountId);
				int row = statement.executeUpdate();
				if (row>0) {
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
	
	public List<Account> getAllAccounts(){
		List<Account> listAccount = null;
		try {
			connection = MyConnection.makeConnection();
			if (connection != null) {
				String sql = "select a.AccountID, r.AccountRoleName "
						+ "from Account a inner join AccountRole r on a.AccountRoleID=r.AccountRoleID ";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					if(listAccount==null) {
						listAccount = new ArrayList<Account>();
					}
					String accountId = resultSet.getString("AccountID");
					String role = resultSet.getString("AccountRoleName");
					listAccount.add(new Account(accountId, role));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listAccount;
	}
	
}
