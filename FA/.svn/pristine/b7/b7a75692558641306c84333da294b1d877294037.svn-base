/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASG1FA_BOOKINGHOTELDB_OBJECT;

import ASG1FA_BOOKINGHOTELDB_DAO.RoleDAO;
import ASG1FA_BOOKINGHOTELDB_DAO.UsersDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Users {
//PROPERTIES

    private String U_Name;
    private String U_Username;
    private String U_Password;
    private String U_Phone;
    private int U_UserID;
//METHOD
//    contructor

    public Users() {
    }

    public Users(String U_Name, String U_Username, String U_Password, String U_Phone) {
        this.U_Name = U_Name;
        this.U_Username = U_Username;
        this.U_Password = U_Password;
        this.U_Phone = U_Phone;
    }
//    getter and setter

    public int getU_UserID() {
        return U_UserID;
    }

    public void setU_UserID(int U_UserID) {
        this.U_UserID = U_UserID;
    }

    public String getU_Name() {
        return U_Name;
    }

    public void setU_Name(String U_Name) {
        this.U_Name = U_Name;
    }

    public String getU_Username() {
        return U_Username;
    }

    public void setU_Username(String U_Username) {
        this.U_Username = U_Username;
    }

    public String getU_Password() {
        return U_Password;
    }

    public void setU_Password(String U_Password) {
        this.U_Password = U_Password;
    }

    public String getU_Phone() {
        return U_Phone;
    }

    public void setU_Phone(String U_Phone) {
        this.U_Phone = U_Phone;
    }
//    login function

    public int loginAccount() throws SQLException, ClassNotFoundException {
        inputUsername();
        inputPassword();
        UsersDAO dao = new UsersDAO();
        int role = dao.checkLogin(U_Username, U_Password);
        if (role == 1) {
            U_UserID = dao.getUserID(U_Username);
        }
        return role;
    }
//    register function

    public int registerAccount() throws SQLException, ClassNotFoundException {

        inputValueResgister();
        UsersDAO dao = new UsersDAO();
        int role =dao.createAccount(U_Username, U_Password, U_Name, U_Phone);
        if(role!=0 ){
            System.out.println("SUCCESS ! PRESS ANY KEY TO CONTINUE ...");
            return role;
        }
        
        return 0;
    }
//    input function

    private void inputValueResgister() {

        inputUsernameRes();
        inputPassword();
        inputName();
        inputTel();

    }

    private void inputUsername() {
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT YOUR USERNAME:\n");
                this.U_Username = s.nextLine();
                error = checkUsername(this.U_Username);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputPassword() {
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT YOUR PASSWORD:\n");
                this.U_Password = s.nextLine();
                error = checkPassword(this.U_Password);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputName() {
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT YOUR NAME:\n");
                this.U_Name = s.nextLine();
                error = checkName(this.U_Name);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputTel() {
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT YOUR PHONE:\n");
                this.U_Phone = s.nextLine();
                error = checkPhone(this.U_Phone);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private void inputUsernameRes() {
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT YOUR USERNAME:\n");
                this.U_Username = s.nextLine();
                error = checkUsernameRes(this.U_Username);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    break;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }

    private boolean inputRole() {
        int role=0;
        Scanner s = new Scanner(System.in);
        String error = "";
        do {
            try {
                System.out.println("PLEASE INPUT THE ROLE YOU WANT TO CHANGE:\n"
                        + "	1.CUSTOMER\n"
                        + "	2.ADMINISTRATOR\n"
                        + "	3.HOTEL CLERK\n"
                        + "----------------------\n"
                        + "PLEASE INPUT THE NUMBER OF THE ROLE :\n");
                role = s.nextInt();
                error = checkRole(role);
                if (error != null) {
                    throw new Exception(error);
                } else {
                    return true;
                }
            } catch (Exception e) {
                s = new Scanner(System.in);
                System.out.println("ERROR: '" + e.getMessage() + "' ! PLEASE TRY AGAIN ");
            }
        } while (true);
    }
//    check valid function

    private String checkUsername(String username) {
        if (username.length() < 6 || username.length() > 50) {
            return "USERNAME's LENGTH IS FROM 6 TO 50 CHARACTERS!";
        }
        return null;
    }

    private String checkPassword(String username) {
        if (username.length() < 6 || username.length() > 50) {
            return "PASSWORD's LENGTH IS FROM 6 TO 50 CHARACTERS!";
        }
        return null;
    }

    private String checkName(String username) {
        if (username.length() < 6 || username.length() > 50) {
            return "NAME's LENGTH IS FROM 6 TO 50 CHARACTERS!";
        }
        return null;
    }
     private String checkRole(int role) throws SQLException, ClassNotFoundException {
         UsersDAO dao = new UsersDAO();
        int thisuserrole = dao.getRole(U_Username);
        if(role == thisuserrole) return "THIS ROLE IS THE OLD ROLE OF THIS USER";
        if(role != 1 && role !=2 && role !=3  )
        {
            return "DONT HAVE THAT ROLE OPTION";
        }
         
        
        return null;
    }
    private String checkPhone(String username) {
        try {
            if (username.length() != 10) {
                return "PHONE's LENGTH IS 10 CHARACTERS!";
            }
            int digit = Integer.parseInt(username);
        } catch (Exception e) {
            return "YOUR PHONE IS NOT VALID";
        }

        return null;
    }

    private String checkUsernameRes(String U_Username) throws SQLException, ClassNotFoundException {
        if (U_Username.length() < 6 || U_Username.length() > 50) {
            return "USERNAME's LENGTH IS FROM 6 TO 50 CHARACTERS!";
        }
        UsersDAO dao = new UsersDAO();
        int userid = dao.getUserID(U_Username);
        
        if (userid != 0) {
            return "THIS USERNAME WAS EXISTED !";
        }
        return null;
    }
//    SET ROLE FUNCTION
    public boolean setRoleUser() throws SQLException, ClassNotFoundException {
        showUsernameAndRole();
        inputUsername();
        UsersDAO dao = new UsersDAO();
        boolean isValid = inputRole();
        String username = U_Username;
        int role= dao.getRole(username);
        if(isValid){
            dao.setRole(username, role);
            System.out.println("SUCCESS ! PRESS ANY KEY TO CONTINUE ...");
        }
        return true;
    }
//    SHOW USER
    public void showUsernameAndRole() throws SQLException, ClassNotFoundException{
        int count = 0;
        UsersDAO dao = new UsersDAO();
        RoleDAO dao1 = new RoleDAO();
        HashMap<String,Integer> map =dao.getUserWithRole();
        System.out.println("ROLE AND USERNAME OF ALL USER : \n");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            count++;
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(count+". USERNAME: "+ key +"\n HAVE ROLE: "+dao1.getRoleName(value)+"\n" );
        }
        System.out.println("______________________________\n");
    }
   

}
