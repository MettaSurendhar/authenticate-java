package com.menu_driven.proj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation{
	
	static String strUserName;
	static String strPassword;
	static String strEmail;
	static String strDob;
	
	public static boolean UserName(String val) {
		
		strUserName = val;
		if (strUserName == null || strUserName.isEmpty()) {
            return false;
        }
		
		String strUserNameRegex = "^([A-Za-z]|[_A-Za-z](?:[_A-Za-z0-9-]+)){5,}$" ;
		Pattern pattern = Pattern.compile(strUserNameRegex);
		Matcher m = pattern.matcher(strUserName);
		return m.matches();
	}
	
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
				+"{7,20}$";
		Pattern pattern = Pattern.compile(strPasswordRegex);
		Matcher m = pattern.matcher(strPassword);
		return m.matches();
		
		
	}
	
	public static boolean Dob(String val) {
		
		strDob = val;
		int iDay = Integer.parseInt(strDob.substring(0,2));
		int iMonth = Integer.parseInt(strDob.substring(3,5));
		int iYear = Integer.parseInt(strDob.substring(6,10));
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
	
	public static boolean Email(String val) {
		
		strEmail = val;
		if (strEmail == null || strEmail.isEmpty()) {
            return false;
        }
		String strEmailRegex = "^[_A-Za-z0-9-]+(?:\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(?:\\.[A-Za-z0-9]+)*(?:\\.[A-Za-z]{2,3})$";
		Pattern pattern = Pattern.compile(strEmailRegex);
		Matcher m = pattern.matcher(strEmail);
		return m.matches();
		
	}

}
