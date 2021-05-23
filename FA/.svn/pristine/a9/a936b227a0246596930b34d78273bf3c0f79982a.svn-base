package com.hungnp.utils;

import java.util.List;

import com.hungnp.dto.Account;
import com.hungnp.dto.Room;

public class ConsoleTable {

	public void displayRoomTable(List<Room> listRoom) {
		String pattern = "| %-7d | %-14s | %-5.2f | %-6s | %-5s |%n";
		System.out.format("+---------+----------------+-------+--------+-------+%n");
		System.out.format("| Room No | Room Type      | Price | Unit   | Empty |%n");
		System.out.format("+---------+----------------+-------+--------+-------+%n");
		listRoom.forEach(
			room -> System.out.format(pattern, 
									room.getRoomNo(), 
									room.getRoomType(),
									room.getPrice(),
									room.getUnit(),
									room.isEmpty())
		);
		System.out.format("+---------+----------------+-------+--------+-------+%n");
	}
	
	public void displayRoomTable(Room room) {
		String pattern = "| %-7d | %-14s | %-5.2f | %-6s | %-5s |%n";
		System.out.format("+---------+----------------+-------+--------+-------+%n");
		System.out.format("| Room No | Room Type      | Price | Unit   | Empty |%n");
		System.out.format("+---------+----------------+-------+--------+-------+%n");
		System.out.format(pattern, room.getRoomNo(), room.getRoomType(), room.getPrice(), room.getUnit(),
				room.isEmpty());
		System.out.format("+---------+----------------+-------+--------+-------+%n");
	}
	
	public void displayAccountTable(List<Account> listAccount) {
		String pattern = "| %-10s | %-8s |%n";
		System.out.format("+------------+----------+%n");
		System.out.format("| Account Id | Role     |%n");
		System.out.format("+------------+----------+%n");
		listAccount.forEach(account -> System.out.format(pattern, account.getAccountId(), account.getRole()));
		System.out.format("+------------+----------+%n");
	}
	
	public void displayRoomForCustomer(List<Room> listRoom) {
		String pattern = "| %-7d | %-14s | %-5.2f | %-6s |%n";
		System.out.format("+---------+----------------+-------+--------+%n");
		System.out.format("| Room No | Room Type      | Price | Unit   |%n");
		System.out.format("+---------+----------------+-------+--------+%n");
		listRoom.forEach(
				room -> System.out.format(pattern, 
										room.getRoomNo(), 
										room.getRoomType(),
										room.getPrice(),
										room.getUnit())
			);
		System.out.format("+---------+----------------+-------+--------+%n");
	}
}
