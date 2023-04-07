package com.menu_driven.proj;

public class Login {
	
	String strUserName;
	String strPassword;
	String strEncryptedPassword;
	boolean boolResult;
	
	// ------------- CONSTRUCTOR ------------ //
	
	Login(String userName,String password){
		this.strUserName = userName;
		this.strPassword = password;
		loginEncrypt();
	}
	
	// ---------------- ENCRYPTING THE PASSWORD ------------------ //
	
	public void loginEncrypt() {
		strEncryptedPassword = Encryption.encrypt(strPassword);
	}
	
	// --------------- CALLING GET METHOD ---------------------- //
	
	public void logGet () {
		
		System.out.println("\n ---------- [ ACCESSING THE DATABASE ....... ]  ---------- \n");
		
		boolResult = DataBase.Select(strUserName,strEncryptedPassword);
		
		if(boolResult)
			System.out.println("\n YOU HAVE LOGED IN SUCCESSFULLY !!! \n");
		else
			System.out.println("\n ENTERED USERNAME OR PASSWORD IS NOT REGISTERED :( \n");

	}
	
	// --------------- CHECKING VALIDATION -------------------- //
	
	public String logValid () {
		
		System.out.println("\n ---------- VALIDATING THE PASSWORD ---------- \n");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.UserName(strUserName);
		if(boolValid == false)
			return "USER NAME";
		
		boolValid = Validation.Password(strPassword);
		if(boolValid == false)
			return "PASSWORD";
		
		return strReturn;
	}
	
	

}
