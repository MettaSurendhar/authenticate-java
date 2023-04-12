package com.menu_driven.proj;

public class Registration {
	String strEmail;
	String strPassword;
	String strDob;
	String strUserName;
	String strEncryptedPassword;


	// ------------- CONSTRUCTOR ------------ //
	
	Registration ( String userName,String password,String email , String dob){
		this.strEmail = email;
		this.strPassword = password;
		this.strDob = dob;
		this.strUserName = userName;
		registEncrypt();
	}
	
	// ---------------- ENCRYPTING THE PASSWORD ------------------ //
	
	public void registEncrypt() {
		strEncryptedPassword = Encryption.encrypt(strPassword);
	}
	
	// --------------- CALLING POST METHOD ---------------------- //
	
	public void registPost() {
		
		System.out.println("\n\t[ ACCESSING THE DATABASE ....... ]\n");

		boolean boolResult  = DataBase.Insert(strUserName,strEncryptedPassword,strEmail,strDob,strPassword);
		
		if(boolResult)
			System.out.println("\n ----------------- YOU HAVE REGISTERED SUCCESSFULLY :) ----------------- \n");
		else
			System.out.println("\n ----------------- YOUR REGISTRATION IS FAILED :( ----------------- \n");
	}
	
	// --------------- CHECKING VALIDATION -------------------- //
	
	public String registValid() {
		System.out.println("\n\t[ VALIDATING THE PASSWORD ....... ]\n");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.Email(strEmail);                                 //
		if(boolValid == false)                                                 // --------------- validating the "mail id" --------------- //
			return "EMAIL";                                                   //
		
		boolValid = Validation.Dob(strDob);                                     //
		if(boolValid == false)                                                 // --------------- validating the "date of birth" --------------- //
			return "DATE OF BIRTH";                                           //
		
		boolValid = Validation.UserName(strUserName);                           //
		if(boolValid == false)                                                 // --------------- validating the "userName" --------------- //
			return "USER NAME";                                               //
		
		boolValid = Validation.Password(strPassword);                           //
		if(boolValid == false)                                                 // --------------- validating the "password" --------------- //
			return "PASSWORD";                                                //
		
		
		return strReturn;
		
	}
	
}
