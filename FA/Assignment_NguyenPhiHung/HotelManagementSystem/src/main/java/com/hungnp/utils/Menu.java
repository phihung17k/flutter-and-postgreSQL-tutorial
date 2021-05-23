package com.hungnp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hungnp.dao.AccountDAO;
import com.hungnp.dao.BookingDAO;
import com.hungnp.dao.RoomDAO;
import com.hungnp.dto.Account;
import com.hungnp.dto.Room;
import com.hungnp.main.HotelManagement;

public class Menu {
	
	public void displayLoginMenu() {
		System.out.println("\nHOTEL MANAGEMENT SYSTEM");
		int choice=0;
		do {
			System.out.println("----------Login Menu----------");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			choice = Utility.inputInteger("Input choice: ", 1, 3);
			switch(choice) {
				case 1:
					String accountId = Utility.inputString("Input accountId: ");
					if(accountId.isEmpty())	break;			
					String password = Utility.inputString("Input password: ");
					if(password.isEmpty())	break;	
					AccountDAO accountDAO = new AccountDAO();
					if(accountDAO.loginAccount(accountId, password)) {
						String role = accountDAO.getRole(accountId);
						HotelManagement.cur_account = new Account(accountId, role);
						choice = 3;
						System.out.println("Login Successfully!!!");
					}else {
						System.out.println("Not found the account!!!");
					}
					break;
				case 2:
					accountDAO = new AccountDAO();
					accountId = Utility.inputStringRegister("Input accountId: ");
					if(accountId.isEmpty())	break;	
					password = Utility.inputStringRegister("\nInput password: ");
					if(password.isEmpty())	break;
					String confirmPassword = Utility.inputString("Input confirm password: ");
					if(!confirmPassword.equals(password)) {
						System.out.println("confirm fail!!!");
					}else if(accountDAO.registerAccount(accountId, password)) {
						HotelManagement.cur_account = new Account(accountId, "Customer");
						choice = 3;
						System.out.println("Register successfully!!!");
					}
					break;
				case 3: 
					HotelManagement.exit = true;
					break;
				default:
					System.out.println("Please input in the range [1, 3]");
			}
		}while(choice>=1 && choice <=2);
	}
	
	public void displayClerkMenu() {
		RoomDAO roomDAO = new RoomDAO();
		ConsoleTable table = new ConsoleTable();
		int choice=0;
		do {
			System.out.println("----------Clerk Menu----------");
			System.out.println("1. View all of rooms");
			System.out.println("2. Search rooms");
			System.out.println("3. Update status of rooms");
			System.out.println("4. Logout");
			choice = Utility.inputInteger("Input choice: ", 1, 4);
			switch(choice) {
				case 1:
					List<Room> listRoom = roomDAO.getAllRooms();
					if(listRoom!=null) {
						table.displayRoomTable(listRoom);
					}else {
						System.out.println("List of room is empty");
					}
					System.out.println();
					break;
				case 2:
					listRoom = new ArrayList<Room>();
					String searchValue = Utility.inputString("Search room no: ");
					if(searchValue.isEmpty()) break;
					listRoom = roomDAO.searchRoomNo(searchValue);
					if(listRoom!=null) {
						table.displayRoomTable(listRoom);
					}else {
						System.out.println("No found the matched room");
					}
					System.out.println();
					break;
				case 3: //set empty or not
					int numOfRoom = roomDAO.countRoom();
					if(numOfRoom>0) {
						int roomNo = Utility.inputInteger("Input room number:", 1, numOfRoom);
						if(roomNo==0) break;
						Room room = roomDAO.getRoom(roomNo);
						table.displayRoomTable(room);
						String status = Utility.inputStringStatus("Set the room to empty [y/n]:");
						if(status.isEmpty()) break;
						if((room.isEmpty() && status.equals("n")) || (!room.isEmpty() && status.equals("y"))){
							roomDAO.updateStatusRoom(roomNo, status);
						}
						System.out.println("Update status successfully!!!");
					}else {
						System.out.println("List of room is empty");
					}
					System.out.println();
					break;
				case 4:
					HotelManagement.cur_account = null;
					break;
				default:
					System.out.println("Please input in the range [1, 3]");
			}
		}while(choice>=1 && choice <=3);
	}
	
	public void displayAdminMenu() {
		AccountDAO accountDAO = new AccountDAO();
		ConsoleTable table = new ConsoleTable();
		int choice=0;
		do {
			System.out.println("----------Admin Menu----------");
			System.out.println("1. Create new user");
			System.out.println("2. Set role");
			System.out.println("3. Logout");
			choice = Utility.inputInteger("Input choice: ", 1, 3);
			switch(choice) {
				case 1:
					accountDAO = new AccountDAO();
					String accountId = Utility.inputStringRegister("Input accountId to create user: ");
					if(accountId.isEmpty())	break;	
					String password = Utility.inputStringRegister("\nInput password: ");
					if(password.isEmpty())	break;
					if(accountDAO.registerAccount(accountId, password)) {
						System.out.println("Create user successfully!!!");
					}else {
						System.out.println("Create fail!!!");
					}
					break;
				case 2:
					accountDAO = new AccountDAO();
					List<Account> listAccount = accountDAO.getAllAccounts();
					if(listAccount!=null) {
						table.displayAccountTable(listAccount);
					}else {
						System.out.println("List of account is empty");
					}
					accountId = Utility.inputString("Input accountId to set role: ");
					if(accountId.isEmpty())	break;	
					if(!accountDAO.checkExistedAccount(accountId)) {
						System.out.println("The accountId is not existed");
						break;
					}else if(accountId.equals(HotelManagement.cur_account.getAccountId())){
						System.out.println("Can't set role the account you're using");
						break;
					}
					String roleName = accountDAO.getRole(accountId);
					System.out.println("Account "+accountId+" has the role is "+roleName);
					int chosenRole = 0;
					System.out.println("=========Choice a role==========");
					System.out.println("1. Admin");
					System.out.println("2. Clerk");
					System.out.println("3. Customer");
					chosenRole = Utility.inputInteger("Input the role: ", 1, 3);
					if(chosenRole == 0) break;
					if(accountDAO.setRole(accountId, chosenRole)) {
						System.out.println("Set role to account "+accountId+" successfully!!!");
					}else {
						System.out.println("Set role fail!!!");
					}
					break;
				case 3:
					HotelManagement.cur_account = null;
					break;
				default:
					System.out.println("Please input in the range [1, 3]");
			}
		}while(choice>=1 && choice <=2);
	}
	
	public void displayCustomerMenu() {
		int choice=0;
		do {
			System.out.println("----------Customer Menu----------");
			System.out.println("1. Booking room");
			System.out.println("2. Logout");
			choice = Utility.inputInteger("Input choice: ", 1, 2);
			switch(choice) {
				case 1:
					String bookingDate = Utility.inputStringDate("Input booking date [YYYY-MM-DD]:", 
													new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					if(bookingDate.isEmpty()) break;
					String checkoutDate = Utility.inputStringDate("Input checkout date [YYYY-MM-DD]:" , bookingDate);
					if(checkoutDate.isEmpty()) break;
					RoomDAO roomDAO = new RoomDAO();
					List<Room> listRoom = roomDAO.getRoomsForCustomer(bookingDate, checkoutDate);
					ConsoleTable table = new ConsoleTable();
					table.displayRoomForCustomer(listRoom);
					int roomNumber = Utility.inputInteger("Input room number:", 1, listRoom.size()+1);
					if(roomNumber==0) break;
					if(!Utility.isEmptyRoom(listRoom, roomNumber)) {
						System.out.println("The room is in use");
					}else {
						BookingDAO bookingDAO = new BookingDAO();
						if(bookingDAO.createBookingRoom(bookingDate, 
														checkoutDate, 
														HotelManagement.cur_account.getAccountId(), 
														roomNumber)) {
							System.out.println("Booking room successfully!!!");
						}else {
							System.out.println("Booking fail!!!");
						}
					}
					break;
				case 2:
					HotelManagement.cur_account = null;
					break;
				default:
					System.out.println("Please input in the range [1, 2]");
			}
		}while(choice==1);
	}
}
