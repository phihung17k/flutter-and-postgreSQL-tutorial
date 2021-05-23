/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_OBJECT;

import ASG1FA_BOOKINGHOTELDB_DAO.RoomDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Rooms {

    int roomnumber;
    String roomtype;
    String customername;
    List<RentedRoom> listrentedroom;
    boolean status;

    public Rooms() {
    }

    public List<RentedRoom> getListrentedroom() {
        return listrentedroom;
    }

    public void setListrentedroom(List<RentedRoom> listrentedroom) {
        this.listrentedroom = listrentedroom;
    }

    public Rooms(int roomnumber, String roomtype, String customername) {
        this.roomnumber = roomnumber;
        this.roomtype = roomtype;
        this.customername = customername;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }
//    METHOD
//    view room func

    public void changeStatus() throws SQLException, ClassNotFoundException {
        inputRoomNumber();
        inputStatus();
        RoomDAO dao = new RoomDAO();
        boolean isvalid=dao.changeStatus(status, roomnumber);
        if (isvalid) System.out.println("DONE.PRESS ANY KEY TO CONTINUE..");
    }

    private String checkInteger(int quantity) {
        if (quantity < 0) {
            return "INVALID QUANTITY ! PLEASE TRY AGAIN";
        }
        return null;
    }

    private String checkInteger2(int quantity) {
        if (quantity < 0) {
            return "INVALID QUANTITY ! PLEASE TRY AGAIN";
        }if(quantity !=1 && quantity !=2)
            return "DO NOT HAVE THIS CHOICE";
        return null;
    }

    private void inputRoomNumber() {
        int quantity = 0;
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE NUMBER OF ROOM:");
                quantity = s.nextInt();
                error = checkInteger(quantity);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    this.roomnumber = quantity;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR QUANTITY: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputStatus() {
        int quantity = 0;
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE STATUS OF ROOM:\n"
                        + " 1.AVAILABLE\n"
                        + " 2.NOT AVAILABLE\n"
                        + "PLEASE CHOICE :\n");

                quantity = s.nextInt();
                error = checkInteger2(quantity);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    if(quantity == 1){
                        status = true;
                    }else{
                        status= false;
                    }
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR QUANTITY: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }
}
