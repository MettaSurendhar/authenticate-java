package com.menu_driven.proj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation{
	
	static String strUserName;
	static String strPassword;
	static String strEmail;
	static String strDob;
	
	// --------------- CHECKING USERNAME VALIDATION -------------------- //
	public static boolean UserName(String val) {
		
		strUserName = val;
		if (strUserName == null || strUserName.isEmpty()) {
            return false;
        }
		
		String strUserNameRegex = "^([A-Za-z]|[_A-Za-z0-9](?:[_A-Za-z0-9-]*)*){7,}$" ;        // -> regular expression
		Pattern pattern = Pattern.compile(strUserNameRegex);                             // -> to generate the pattern from the regular expression
		Matcher m = pattern.matcher(strUserName);                                       // -> match the value with the pattern 
		return m.matches();                                                            // -> returns true if matched  or false if not 
	}
	
	// --------------- CHECKING PASSWORD VALIDATION -------------------- //
	
	public static boolean Password (String val) {
		
		strPassword = val;
		if (strPassword == null || strPassword.isEmpty()) {
            return false;
        }
		

		String strPasswordRegex = "^(?=.*[0-9])"
				+"(?=.*[a-z])"
				+"(?=.*[A-Z])"
				+"(?=.*[!@#&()–[{}]:;',?/~$^+=<>])" 
				+"."
				+"{7,20}$";                                                            // -> regular expression
		Pattern pattern = Pattern.compile(strPasswordRegex);                          // -> to generate the pattern from the regular expression
		Matcher m = pattern.matcher(strPassword);                                    // -> match the value with the pattern 
		return m.matches();                                                         // -> returns true if matched  or false if not 
		
		
	}
	
	// --------------- CHECKING EMAIL VALIDATION -------------------- //
	
	public static boolean Email(String val) {
		
		strEmail = val;
		if (strEmail == null || strEmail.isEmpty()) {
            return false;
        }
		String strEmailRegex = "^[_A-Za-z0-9-]+(?:\\.[_A-Za-z0-9-]+)*@" 
								+ "[A-Za-z0-9-]+(?:\\.[A-Za-z0-9]+)*"
								+ "(?:\\.[A-Za-z]{2,3})$";                           // -> regular expression
		Pattern pattern = Pattern.compile(strEmailRegex);                           // -> to generate the pattern from the regular expression
		Matcher m = pattern.matcher(strEmail);                                     // -> match the value with the pattern 
		return m.matches();                                                       // -> returns true if matched  or false if not 
		
	}
	
	// --------------- CHECKING DATE_OF_BIRTH VALIDATION -------------------- //
	
	public static boolean Dob(String val) {
		
		strDob = val;
		int iDay;
		int iMonth;
		int iYear;
		
		               // ------ split the user entered date into day , month and year -------- //
		try {
			
			iDay = Integer.parseInt(strDob.substring(0,2));
			iMonth = Integer.parseInt(strDob.substring(3,5));
			iYear = Integer.parseInt(strDob.substring(6,10));
			
		}
		catch(Exception e) {
			
			System.out.println(e);
			return false;
			
		}
		
		      // ------------ checking whether the month entered has " TOTALY 30 or 31 DAYS " and also whether the year is "LEAP YEAR" or not  -------------- //
		
		if(iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11) {
			if(iDay == 31) {
				return false;
			}
		}
		if(iMonth == 2) {
			if((iYear %4 == 0) && (iYear%100 !=0) || (iYear%400 == 0) ) {
				if (iDay > 29) 
					return false;
			}
			else {
				if ( iDay >= 29) 
					return false;
			}
		}
		
		return true;
		
	}
	

}
