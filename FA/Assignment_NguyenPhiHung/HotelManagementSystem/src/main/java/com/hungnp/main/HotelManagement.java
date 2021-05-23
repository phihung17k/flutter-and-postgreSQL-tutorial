package com.hungnp.main;

import com.hungnp.dto.Account;
import com.hungnp.utils.Menu;

public class HotelManagement {
	public static Account cur_account = null;
	public static boolean exit = false;

	public static void main(String[] args) {
		Menu menu = new Menu();
		do {
			menu.displayLoginMenu();
			if (cur_account != null) {
				System.out.println("\nWelcome " + HotelManagement.cur_account.getAccountId());
				if (cur_account.getRole().equals("Admin")) {
					menu.displayAdminMenu();
				} else if (cur_account.getRole().equals("Clerk")) {
					menu.displayClerkMenu();
				} else { // Customer
					menu.displayCustomerMenu();
				}
			}
		} while (!exit);
		System.out.println("See you again!!!");
		
		
		
	}

}
