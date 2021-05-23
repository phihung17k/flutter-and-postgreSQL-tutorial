/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Validation {
    public static boolean isName(String str) {
        String regex = "^([a-zA-Z ]){1,50}$";
        Pattern template = Pattern.compile(regex);
        Matcher matcher = template.matcher(str);
        return matcher.matches();
    }
    
    public static boolean isEmail(String str) {
        String regex = "^([a-zA-Z0-9(_|.)]+)@([a-zA-Z]+)(.[a-zA-Z]+)+$";
        Pattern template = Pattern.compile(regex);
        Matcher matcher = template.matcher(str);
        return matcher.matches();
    }
    
    public static boolean isPhoneNumber(String str) {
        String regex = "^(0\\d{9})|(\\+\\d{1,3}\\d{6,8})$";
        Pattern template = Pattern.compile(regex);
        Matcher matcher = template.matcher(str);
        return matcher.matches();
    }
    
    public static boolean isPassword(String str) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern template = Pattern.compile(regex);
        Matcher matcher = template.matcher(str);
        return matcher.matches();
    }
}
