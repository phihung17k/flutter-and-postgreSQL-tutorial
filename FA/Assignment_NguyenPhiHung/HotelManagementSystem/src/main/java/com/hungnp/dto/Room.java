package com.hungnp.dto;

import java.io.Serializable;

public class Room implements Serializable{
	
	private int roomNo;
	private String roomType;
	private double price;
	private String unit;
	private boolean isEmpty;
	
	public Room() {
	}
	
	

	public Room(int roomNo, String roomType, double price, String unit) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.price = price;
		this.unit = unit;
	}

	public Room(int roomNo, String roomType, double price, String unit, boolean isEmpty) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.price = price;
		this.unit = unit;
		this.isEmpty = isEmpty;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
}
