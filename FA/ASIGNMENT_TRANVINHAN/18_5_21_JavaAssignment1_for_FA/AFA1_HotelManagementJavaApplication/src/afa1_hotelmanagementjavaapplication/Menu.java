/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa1_hotelmanagementjavaapplication;

import ASG1FA_BOOKINGHOTELDB_OBJECT.RentedRoom;
import ASG1FA_BOOKINGHOTELDB_OBJECT.RoomList;
import ASG1FA_BOOKINGHOTELDB_OBJECT.Rooms;
import ASG1FA_BOOKINGHOTELDB_OBJECT.Users;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Menu {

    static void excApplication() {
        Scanner s = new Scanner(System.in);
        int choice = 0, loginorregisterchoice = 0;
        loginorregisterchoice = printMenuforLoginandRegister();

        int role = 0, userid = 0;
        do {
            try {

                if (loginorregisterchoice == 1) {
                    Users tmp = new Users();
                    System.out.println("___________LOGIN  FORM__________");
                    role = tmp.loginAccount();
                    userid = tmp.getU_UserID();
                } else {
                    Users tmp = new Users();
                    System.out.println("___________RESGITERATION FORM__________");
                    userid = tmp.registerAccount();
                    role = 1;

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } while (role == 0);
        do {
            if (role == 1) {
                printMenuForCustomer(userid);
            } else if (role == 2) {
                try {
                    choice = printMenuForAdministrator();
                    switch (choice) {
                        case 1: {
                            Users tmp = new Users();
                            System.out.println("___________CREATE NEW USER FORM__________");
                            userid = tmp.registerAccount();
                            break;

                        }
                        case 2: {
                            Users tmp = new Users();
                            System.out.println("___________SET ROLE FORM__________");
                            tmp.setRoleUser();
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (role == 3) {
               
                try {
                    choice = printMenuForHotelClerk();
                    switch (choice) {
                        case 1: {
                            RoomList roomlist = new RoomList();
                            System.out.println("___________ALL OF ROOM IN HOTEL__________");
                            roomlist.viewRoomlist();
                            break;

                        }
                        case 2: {
                            RoomList roomlist = new RoomList();
                            System.out.println("___________SEARCH ROOM__________");
                            roomlist.searchRoom();
                            break;
                        }
                        case 3:{
                            Rooms room = new Rooms();
                            System.out.println("___________STATUS ROOM__________");
                            room.changeStatus();
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("How u get in here ???");
            }
            s = new Scanner(System.in);
            s.nextLine();
        } while (true);
    }

    static int printMenuforLoginandRegister() {
        Scanner s = new Scanner(System.in);
        int choice = 0;
        String errormessage = "";
        do {
            try {
                System.out.println("______________HOTEL MANAGEMENT SYSTEM______________\n"
                        + "PLEASE LOGIN TO YOUR ACCOUNT OR REGISTER A NEW ACCOUNT BEFORE USING OUR FUNCTION\n"
                        + "\n"
                        + " 1.LOGIN \n"
                        + " 2.REGISTER\n"
                        + "_________________________________________________________\n"
                        + "ENTER 0 TO EXIT\n"
                        + "PLEASE TYPING YOUR CHOICE: ");

                choice = s.nextInt();
                errormessage = checkLoginChoice(choice);
                if (!errormessage.isEmpty()) {
                    throw new Exception(errormessage);
                } else {
                    s = new Scanner(System.in);
                    return choice;
                }

            } catch (Exception e) {
                System.out.println("SYSTEM HAS A ERROR: '" + e.getMessage() + "' PLEASE TRY AGAIN !");
                s = new Scanner(System.in);
                s.nextLine();

            }
        } while (true);
    }

    private static void printMenuForCustomer(int userid) {
        try {
            System.out.println("______HOTEL MANAGEMENT SYSTEM |ROLE CUSTOMER______\n"
                    + "\n"
                    + "__________________BOOKING ROOM FORM__________________");
            RentedRoom rr = new RentedRoom();

            int RoomNumber = rr.bookingRoom(userid);
            System.out.println("__________________\n"
                    + "PRESS ANY KEY TO CONTINUE...\n");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int printMenuForAdministrator() {
        int choice = 0;
        String errormessage = "";
        Scanner s = new Scanner(System.in);
        do {
            try {
                System.out.println("___HOTEL MANAGEMENT SYSTEM |ROLE ADMINOSTRATOR___\n"
                        + "\n"
                        + "	1. Create new user\n"
                        + "	2. Set Role \n"
                        + "_________________________________________________________\n"
                        + "ENTER 0 TO EXIT\n"
                        + "PLEASE TYPING YOUR CHOICE: ");
                choice = s.nextInt();
                errormessage = checkAdminsChoice(choice);
                if (!errormessage.isEmpty()) {
                    throw new Exception(errormessage);
                } else {
                    s = new Scanner(System.in);

                    return choice;
                }
            } catch (Exception e) {
                System.out.println("SYSTEM HAS A ERROR: '" + e.getMessage() + "' PLEASE TRY AGAIN !");
                s = new Scanner(System.in);
                s.nextLine();
            }
        } while (true);
    }

    private static int printMenuForHotelClerk() {
        int choice = 0;
        String errormessage = "";
        Scanner s = new Scanner(System.in);
        do {
            try {
                System.out.println("______HOTEL MANAGEMENT SYSTEM |ROLE HOTEL CLERK______\n"
                        + "\n"
                        + "	1. View all of rooms\n"
                        + "	2. Search rooms \n"
                        + "	3. Update status of rooms \n"
                        + "_________________________________________________________\n"
                        + "ENTER 0 TO EXIT\n"
                        + "PLEASE TYPING YOUR CHOICE: ");
                choice = s.nextInt();
//                System.out.println("choice"+ choice);
                errormessage = checkClerkChoice(choice);
                if (!errormessage.isEmpty()) {
                    
                    throw new Exception(errormessage);
                } else {
                    return choice;
                }
            } catch (Exception e) {
                System.out.println("SYSTEM HAS A ERROR: '" + e.getMessage() + "' PLEASE TRY AGAIN !");
                s = new Scanner(System.in);
                s.nextLine();
            }
        } while (true);
    }

    static String checkLoginChoice(int choice) {
        String choicestring = "";
        if (choice == 0) {
            System.out.println("BYE\n");
            exit(0);
        }
        if (choice != 1 && choice != 2) {
            return "DO NOT HAVE THIS CHOICE";
        }
        return choicestring;
    }

    static String checkAdminsChoice(int choice) {
        String choicestring = "";
        if (choice == 0) {
            System.out.println("BYE\n");
            exit(0);
        }
        if (choice != 1 && choice != 2) {
            return "DO NOT HAVE THIS CHOICE";
        }
        return choicestring;
    }

    private static String checkClerkChoice(int choice) {
         String choicestring = "";
        if (choice == 0) {
            System.out.println("BYE\n");
            exit(0);
        }
        if (choice != 1 && choice != 2 && choice !=3) {
            return "DO NOT HAVE THIS CHOICE";
        }
        return choicestring;
    }
}
