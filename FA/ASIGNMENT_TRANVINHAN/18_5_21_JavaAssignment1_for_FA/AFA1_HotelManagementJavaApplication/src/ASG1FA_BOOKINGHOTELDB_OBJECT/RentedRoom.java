/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_OBJECT;

import ASG1FA_BOOKINGHOTELDB_DAO.RentedRoomDAO;
import ASG1FA_BOOKINGHOTELDB_DAO.UsersDAO;
import static java.lang.System.exit;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class RentedRoom {
//PROPERTIES

    int RoomID;
    int UserID;
    Date CheckinDate;
    Date CheckoutDate;
    int RoomTypeID;
    int QualityofRoom;
//    METHOD
//    Contructor

    public RentedRoom() {
    }

    public RentedRoom(int RoomID, int UserID, Date CheckinDate, Date CheckinOut) {
        this.RoomID = RoomID;
        this.UserID = UserID;
        this.CheckinDate = CheckinDate;
        this.CheckoutDate = CheckinOut;
    }
//    GETTER AND SETTER

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public Date getCheckinDate() {
        return CheckinDate;
    }

    public void setCheckinDate(Date CheckinDate) {
        this.CheckinDate = CheckinDate;
    }

    public Date getCheckinOut() {
        return CheckoutDate;
    }

    public void setCheckinOut(Date CheckinOut) {
        this.CheckoutDate = CheckinOut;
    }
//    BOOKING FUNCTION

    public int bookingRoom(int userid) throws SQLException, ClassNotFoundException {
        int roomnumber = 0, roomid = 0;
        this.UserID = userid;
        int quantity = 0;
        inputValue();
        RentedRoomDAO dao = new RentedRoomDAO();
        quantity = dao.checkRoomAvailable(RoomTypeID, CheckinDate, CheckoutDate, QualityofRoom);
//        System.out.println(quantity);
        if (quantity > 0 && quantity >= QualityofRoom) {
            int quantityleft = QualityofRoom;
            do {
                roomid = dao.getRoomIDAvailable(RoomTypeID, CheckinDate, CheckoutDate);
                if (roomid != 0) {

                    dao.addRentedRoom(roomid, userid, CheckinDate, CheckoutDate);
                    roomnumber = dao.getRoomnumberAvailable(roomid);
                    UsersDAO userdao = new UsersDAO();

                    System.out.println(quantityleft + ". ROOM WAS RENTED \n"
                            + "CUSTOMER'S NAME: " + userdao.getUName(userid) + "\n"
                            + "ROOM NUMBER : " + roomnumber + " \n"
                            + "ROOM TYPE: " + dao.getRoomtypeNameAvailable(RoomTypeID) + "\n"
                            + "ROOM PRICE:" + dao.getRoompriceAvailable(RoomTypeID) + "\n"
                            + "CHECK IN DATE : " + CheckinDate + "\n"
                            + "CHECK OUT DATE: " + CheckoutDate);
                    quantityleft--;
                } else {
                    System.out.println("Something went wrong!" + roomid);
                }
            } while (quantityleft != 0);
        } else {
            if (quantity <= 0) {
                System.out.println("OUT OF ROOM!PLEASE TRY AGAIN");
            } else {
                System.out.println("THERE ARE OLNY " + quantity + " ROOMS LEFT !CAN NOT TO RENT. PLEASE TRY AGAIN");
            }
        }
        return roomnumber;
    }
//    INPUT FUNCTION

    private void inputValue() throws SQLException, ClassNotFoundException {

        inputRoomType();
        inputQuality();
        inputCheckInDate();
        inputCheckOutDate();

    }

    private void inputRoomType() {
        int choice = 0;
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE ROOM TYPE YOU WANT:\n"
                        + "	1.Single\n"
                        + "	2.Double\n"
                        + "	3.Twin\n"
                        + "	4.Double Double\n"
                        + "	5.Quad\n"
                        + "_________________________________________________________\n"
                        + "ENTER 0 TO EXIT\n"
                        + "PLEASE TYPING YOUR CHOICE: ");
                choice = s.nextInt();
                error = checkChoice(choice);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    this.RoomTypeID = choice;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR INPUT: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputCheckInDate() {
        Date tmpdate = null;
        Scanner s = new Scanner(System.in);
        String error = "", dateString = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE CHECK IN DATE (dd/MM/yyyy):");
                dateString = s.nextLine();
                error = checkCheckinDate(dateString);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    tmpdate = new Date(sdf.parse(dateString).getTime());
                    this.CheckinDate = tmpdate;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR CHECK IN: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
                //e.printStackTrace();
            }
        } while (true);
    }

    private void inputCheckOutDate() {
        Date tmpdate = null;
        Scanner s = new Scanner(System.in);
        String error = "", dateString = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE CHECK OUT DATE (dd/MM/yyyy):");
                dateString = s.nextLine();
                error = checkCheckoutDate(dateString);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    tmpdate = new Date(sdf.parse(dateString).getTime());
                    this.CheckoutDate = tmpdate;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputQuality() {
        int quantity = 0;
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE QUANITY OF ROOM:");
                quantity = s.nextInt();
                error = checkQuantity(quantity);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    this.QualityofRoom = quantity;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR QUANTITY: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }
//    CHECK VALID FUNCTION

    private String checkCheckinDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date tmpdate = new Date(sdf.parse(dateString).getTime());
            Date currentdate = new Date(Calendar.getInstance().getTimeInMillis());
            String current = sdf.format(currentdate);
            currentdate = new Date(sdf.parse(current).getTime());

            if (tmpdate.before(currentdate)) {
                return "CHECK IN DATE IS SMALLER THAN CURRENT DATE.";
            }
        } catch (ParseException ex) {
            return "CHECK IN DATE IS NOT IN FORMAT";
        }
        return null;
    }

    private String checkCheckoutDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date tmpdate = new Date(sdf.parse(dateString).getTime());

            Date checkindate = this.CheckinDate;
            if (!tmpdate.after(checkindate)) {
                return "CHECK OUT DATE IS SMALLER THAN CHECK IN DATE";
            }
        } catch (ParseException ex) {
            return "CHECK OUT DATE IS NOT IN FORMAT";
        }
        return null;
    }

    private String checkQuantity(int quantity) {
        if (quantity < 0) {
            return "INVALID QUANTITY ! PLEASE TRY AGAIN";
        }
        return null;
    }

    private String checkChoice(int choice) {
        if (choice == 0) {
            exit(0);
        }
        if (choice < 0 || choice > 5) {
            return "WRONG CHOICE ! PLEASE TRY AGAIN";
        }
        return null;
    }

    @Override
    public String toString() {
        try {
            UsersDAO dao = new UsersDAO();
            RentedRoomDAO dao1 = new RentedRoomDAO();
            return "RentedRoom{" + "RoomID=" + dao1.getRoomnumberAvailable(RoomID) + ", UserID=" + dao.getUName(UserID) + ", CheckinDate=" + CheckinDate + ", CheckoutDate=" + CheckoutDate + '}';
        } catch (SQLException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
        }
        return null;
    }

}
