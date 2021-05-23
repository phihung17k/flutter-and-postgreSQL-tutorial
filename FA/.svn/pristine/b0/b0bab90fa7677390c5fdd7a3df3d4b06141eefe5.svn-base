package com.hungnp.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.validator.GenericValidator;

import com.hungnp.dao.AccountDAO;
import com.hungnp.dto.Room;

public class Utility {
	
	public static int inputInteger(String title, int start, int end) {
 		Scanner sc = new Scanner(System.in);
		int rs = 0;
		boolean isValid = false;
		do {
			System.out.print(title);
			String num = sc.nextLine().trim();
			if(num.matches("\\d+")) {
				rs = Integer.parseInt(num);
				if(rs >= start && rs <= end) {
					isValid = true;
				}
			}
			if(!isValid) {
				if(title.equals("Input room number:")) { //get room No
					System.out.printf("The room number is not exist!!! Range of room is [%s, %s]\n", start, end);
					String answer="";
					do {
						System.out.println("Do you want to continue?[y/n]: ");
						answer = sc.nextLine();
						if(answer.equals("n")) {
							isValid=true;
							rs=0;
						}
					}while(!answer.equals("y") && !answer.equals("n"));
				}else {	//choice
					System.out.printf("Please input a number in the range [%s, %s]\n", start, end);
				}
			}
		}while(!isValid);
		return rs;
	}
	
	public static String inputString(String title) {
 		Scanner sc = new Scanner(System.in);
		String rs = "";
		boolean isValid = false;
		do {
			System.out.print(title);
			rs = sc.nextLine().trim();
			if(!rs.trim().isEmpty()) {
				isValid = true;
			}
			if(!isValid) {
				System.out.println("Input is not empty, please input again");
				String answer="";
				do {
					System.out.println("Do you want to continue?[y/n]: ");
					answer = sc.nextLine();
					if(answer.equals("n")) {
						isValid=true;
						rs="";
					}
				}while(!answer.equals("y") && !answer.equals("n"));
			}
		}while(!isValid);
		return rs;
	}
	
	public static String inputStringRegister(String title) {
 		Scanner sc = new Scanner(System.in);
		String rs = "";
		boolean isValid = false;
		do {
			System.out.print(title);
			rs = sc.nextLine().trim();
			if(rs.trim().isEmpty()) {
				System.out.println("Input is not empty, please input again");
			}else if (!rs.matches("\\w+")) {
				System.out.println("Invalid input, please input a sequence of characters such as abc123");
			}else if(rs.length()>16){
				System.out.println("The characters is more than the range input (max is 16), please input again");
			}else if(title.contains("accountId")) {
				AccountDAO accountDAO = new AccountDAO();
				if(accountDAO.checkExistedAccount(rs)) {
					System.out.println("The accountId is existed, please input again");
				}else {
					isValid = true;
				}
			}else {
				isValid = true;
			}
			if(!isValid) {
				String answer="";
				do {
					System.out.println("Do you want to continue?[y/n]: ");
					answer = sc.nextLine();
					if(answer.equals("n")) {
						isValid=true;
						rs="";
					}
				}while(!answer.equals("y") && !answer.equals("n"));
			}
		}while(!isValid);
		return rs;
	}
	
	public static String inputStringStatus(String title) {
 		Scanner sc = new Scanner(System.in);
		String rs = "";
		boolean isValid = false;
		do {
			System.out.print(title);
			rs = sc.nextLine().trim();
			if(rs.trim().isEmpty()) {
				System.out.println("Input is not empty, please input again");
			}else if(!rs.equals("n") && !rs.equals("y")) {
				System.out.println("Invalid input, please input \"y\" (Yes) or \"n\" (No) ");
			}else {
				isValid = true;
			}
			if(!isValid) {
				String answer="";
				do {
					System.out.println("Do you want to continue?[y/n]: ");
					answer = sc.nextLine();
					if(answer.equals("n")) {
						isValid=true;
						rs="";
					}
				}while(!answer.equals("y") && !answer.equals("n"));
			}
		}while(!isValid);
		return rs;
	}
	
	public static String inputStringDate(String title, String minDate) {
		Scanner sc = new Scanner(System.in);
		String rs = "";
		boolean isValid = false;
		do {
			System.out.println(title);
			rs = sc.nextLine().trim();
			if(rs.trim().isEmpty()) {
				System.out.println("Input is not empty, please input again");
			}else if(!GenericValidator.isDate(rs, "yyyy-MM-dd", false)) {
				System.out.println("Invalid date");
			}else if(convertStringToDate(rs).compareTo(convertStringToDate(minDate))<0){
				System.out.println("the input date must be more than or equals "+minDate);
			}else {
				isValid=true;
			}
			if(!isValid) {
				String answer = "";
				do {
					System.out.println("Do you want to continue?[y/n]: ");
					answer = sc.nextLine();
					if(answer.equals("n")) {
						isValid=true;
						rs="";
					}
				}while(!answer.equals("y") && !answer.equals("n"));
			}
		}while(!isValid);
		return rs;
	}
	
	public static Date convertStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static boolean isEmptyRoom(List<Room> listRoom, int roomNumber) {//list the empty room
		for (Room room : listRoom) {
			if(room.getRoomNo() == roomNumber){
				return true;
			}
		}
		return false;
	}
}
