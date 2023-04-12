package com.menu_driven.proj;

//import java.sql.SQLException;

public class ResetPassword {
	
	String strUserName;
	String strPassword;
	String strEncryptedPassword;
//	String strToEmail;
	
	ResetPassword(String userName,String password){
		this.strUserName = userName;
		this.strPassword = password;
	}
	
	// ------------------------- CREATING OTP AND SENDING TO THE USER EMAIL -------------------------- //
	
	public int resetOtp(){
		
		int iMin = 1000;                                                              //
		int iMax = 9999;                                                             // ------------ generating 0TP for password reseting  ------------ //
		int iRange = iMax - iMin + 1;                                               //
		int iOtp = (int)(Math.random() * iRange) + iMin;                           //
		
		String strToEmail = DataBase.SelectEmail(strUserName);                   // ------------- getting email address of the user from the database ------------ //
		Email.sendMail(strToEmail,iOtp);                                        // ------------- sending email and generated OTP to the email method ------------- //
		
		return iOtp ;
		
	}
	
	// ------------------------- CHECKING VALIDATION -------------------------- //
	
	
			// -> CHECKING THE  VALIDATION OF BOTH " USER NAME " AND " PASSWORD "
	
		
	public String resetLogValid () {
		
		// System.out.println("\n\t[ VALIDATING THE PASSWORD ....... ]\n");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.UserName(strUserName);                           //
		if(boolValid == false)                                                 // --------------- validating the "userName" --------------- //
			return "USER NAME";                                               //
		
		boolValid = Validation.Password(strPassword);                           //
		if(boolValid == false)                                                 // --------------- validating the "password" --------------- //
			return "PASSWORD";                                                //
		
		return strReturn;
	}
	
	
			// -> CHECKING THE  VALIDATION OF ONLY " USER NAME "
	
	
	public String resetLogValid (String password) {
		
		// System.out.println("\n\t[ VALIDATING THE PASSWORD ....... ]\n");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.Password(strPassword);                         //
		if(boolValid == false)                                               // --------------- validating the "password" --------------- //
			return "PASSWORD";                                             //
		
		return strReturn;
	}
	
	
	// ---------------- ENCRYPTING THE PASSWORD ------------------ //
	
	
	public String resetEncrypt(String password) {
		String strEncryptedPassword = Encryption.encrypt(password);
		return strEncryptedPassword;
	}
	
	
	// --------------- CALLING POST METHOD ---------------------- //
	
	
	public void resetPost(String password) {
		
		String strNewPassword = password;
		strEncryptedPassword = resetEncrypt(strNewPassword);
		
		// System.out.println("\n\t[ ACCESSING THE DATABASE ....... ]\n");

		boolean boolResult  = DataBase.Update(strUserName, strEncryptedPassword, strNewPassword);          // --------------- updating the password and encrypted password to the database -------------- //
		
		if(boolResult)
			System.out.println("\n ----------------- YOU HAVE SET YOUR PASSWORD SUCCESSFULLY :) ----------------- \n");
		else
			System.out.println("\n ----------------- YOUR PASSWORD RESET IS FAILED :( ----------------- \n");
	}

}
