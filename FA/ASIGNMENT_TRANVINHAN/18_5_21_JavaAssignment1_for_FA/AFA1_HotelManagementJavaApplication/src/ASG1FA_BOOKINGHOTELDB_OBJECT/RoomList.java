/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_OBJECT;

import ASG1FA_BOOKINGHOTELDB_DAO.RentedRoomDAO;
import ASG1FA_BOOKINGHOTELDB_DAO.RoomDAO;
import ASG1FA_BOOKINGHOTELDB_DAO.RoomTypeDAO;
import static java.lang.System.exit;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class RoomList {

    List<Rooms> list;
    int Quantity;
    int searchroomnumber = 0;
    int searchroomtype = 0;
    Date searchcheckin;
    Date searchcheckout;

    public RoomList() throws SQLException, ClassNotFoundException {
        RoomDAO dao = new RoomDAO();
        list = dao.getRooms();
        Quantity = list.size();
    }

    public List<Rooms> getList() {
        return list;
    }

    public void setList(List<Rooms> list) {
        this.list = list;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void viewRoomlist() {
        if (list.isEmpty()) {
            System.out.println("DONT HAVE ANY ROOM IS MATCH THE SEARCH VALUE. PRESS ANY KEY TO CONTINUE...\n");
        } else {
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                count++;
                Rooms get = list.get(i);
                List<RentedRoom> rr = get.getListrentedroom();
                System.out.println(count + ". Room Number: " + get.getRoomnumber() + "\n"
                        + "Room Type:" + get.getRoomtype() + "\n"
                        + "Customer In Room Today : " + get.getCustomername() + "\n");
                for (int j = 0; j < rr.size(); j++) {
                    RentedRoom get1 = rr.get(j);
                    System.out.println("        " + get1.toString() + "\n");
                }
            }
            System.out.println("PRESS ANY KEY TO CONTINUE ...\n");
        }
    }

    public void searchRoom() throws SQLException, ClassNotFoundException {
        List<Rooms> tmplist = new ArrayList<>();
        List<Rooms> tmplist2 = new ArrayList<>();
        List<Rooms> tmplist3 = new ArrayList<>();
        inputSearchValue();
        int count = 0;
        if (searchroomnumber != 0) {
            RentedRoomDAO dao = new RentedRoomDAO();
//            System.out.println(searchroomnumber);
            dao.getRoomnumberAvailable(searchroomnumber);
            for (int i = 0; i < list.size(); i++) {
                Rooms get = list.get(i);
                if (get.getRoomnumber() == searchroomnumber) {
                    count++;
                    tmplist.add(get);
                }
            }
            if (count == 0) {
                System.out.println("DONT HAVE ANY ROOM HAVE THIS ROOM NUMBER");
            }

        } else {
            tmplist = list;
        }
        if (searchroomtype != 0) {
            RoomTypeDAO dao = new RoomTypeDAO();
            String tmptype = dao.getRoomtypeNameAvailable(searchroomtype);
            for (int i = 0; i < tmplist.size(); i++) {
                Rooms get = tmplist.get(i);
                if (get.getRoomtype().equals(tmptype)) {
                    tmplist2.add(get);
                }
            }
        } else {
            tmplist2 = tmplist;

        }
        if (searchcheckin != null && searchcheckout != null) {

            for (int i = 0; i < tmplist2.size(); i++) {
                Rooms get = tmplist2.get(i);
                List<RentedRoom> list2 = get.listrentedroom;
                for (int j = 0; j < list2.size(); j++) {
                    RentedRoom get1 = list2.get(j);
                    if (!get1.CheckinDate.after(searchcheckin) && !get1.CheckoutDate.before(searchcheckout)) {
                        tmplist3.add(get);
                        break;
                    }
                }
            }
            tmplist3 = tmplist2;
        }

        list = tmplist3;
        System.out.println("LIST OF RESEARCH:\n");
        viewRoomlist();
    }

    private void inputSearchValue() {
        Scanner s = new Scanner(System.in);
        String error = "", choice = "";
        do {
            try {
                System.out.println("INPUT SEARCH FILTER YOU WANT TO SEARCH:\n"
                        + " a.ROOM NUMBER\n"
                        + " b.ROOM TYPE\n"
                        + " c.DATE\n"
                        + "__________________\n"
                        + "PLEASE INPUT YOUR CHOICE (multiple choice like 'a,b,c'):");
                choice = s.nextLine();
                error = checkSearchChoice(choice.toLowerCase());
                if (error == null) {
                    String choice1 = choice.toLowerCase();
                    if (choice1.contains("a")) {
                        inputRoomNumber();
                    }
                    if (choice1.contains("b")) {
                        inputRoomType();
                    }
                    if (choice1.contains("c")) {
                        inputCheckInDate();
                        inputCheckOutDate();
                    }
                    return;
                } else {
                    throw new Exception(error);
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: " + e.getMessage() + " PLEASE TRY AGAIN !");
                s.nextLine();
            }
        } while (true);
    }

    private String checkSearchChoice(String choice) {
        if (!choice.contains("a") && !choice.contains("b") && !choice.contains("c")) {
            return "DONT HAVE THAT CHOICE";
        }
        return null;
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
                    this.searchcheckin = tmpdate;
                    break;
                }
            } catch (Exception e) {
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
                    this.searchcheckout = tmpdate;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
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
                    this.searchroomnumber = quantity;
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR QUANTITY: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputRoomType() {
        int quantity = 0;
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
                quantity = s.nextInt();
                error = checkChoice(quantity);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    this.searchroomtype = quantity;
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

        } catch (ParseException ex) {
            return "CHECK IN DATE IS NOT IN FORMAT";
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

    private String checkCheckoutDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date tmpdate = new Date(sdf.parse(dateString).getTime());

            Date checkindate = this.searchcheckin;
            if (!tmpdate.after(checkindate)) {
                return "CHECK OUT DATE IS SMALLER THAN CHECK IN DATE";
            }
        } catch (ParseException ex) {
            return "CHECK OUT DATE IS NOT IN FORMAT";
        }
        return null;
    }

    private String checkInteger(int quantity) {
        if (quantity < 0) {
            return "INVALID QUANTITY ! PLEASE TRY AGAIN";
        }
        return null;
    }
}
