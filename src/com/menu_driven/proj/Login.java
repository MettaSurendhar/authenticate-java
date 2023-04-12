package com.menu_driven.proj;

public class Login {
	
	String strUserName;
	String strPassword;
	String strEncryptedPassword;
	boolean boolResult;
	
	// ------------- CONSTRUCTORS ------------ //
	
	Login(String userName,String password){
		this.strUserName = userName;
		this.strPassword = password;
		loginEncrypt();
	}
	
	Login(String userName){
		this.strUserName = userName;
	}
	
	// ---------------- ENCRYPTING THE PASSWORD ------------------ //
	
	public void loginEncrypt() {
		strEncryptedPassword = Encryption.encrypt(strPassword);
	}
	
	// ------------------------- CALLING GET METHOD -------------------------- //
	
		// -> LOGGING IN BY ENTERNG BOTH " USER NAME " AND " PASSWORD " OF THE USER
	
	public void logGet () {
		
		// System.out.println("\n\t[ ACCESSING THE DATABASE ....... ]\n");
		
		boolResult = DataBase.SelectEncrypt(strUserName,strEncryptedPassword);         
		
		if(boolResult)
			System.out.println("\n ----------------- YOU HAVE LOGED IN SUCCESSFULLY :) ----------------- \n");
		else
			System.out.println("\n ----------------- ENTERED USERNAME OR PASSWORD IS NOT REGISTERED :( ----------------- \n");

	}
	
		// -> LOGGING IN BY ONLY ENTERING THE " USER NAME " OF THE USER
	
	public void logRemindGet () {
		
		// System.out.println("\n\t[ ACCESSING THE DATABASE ....... ]\n");
		
		boolResult = DataBase.SelectEncrypt(strUserName);
		
		if(boolResult)
			System.out.println("\n ----------------- YOU HAVE LOGED IN SUCCESSFULLY :) ----------------- \n");
		else
			System.out.println("\n ----------------- ENTERED USERNAME OR PASSWORD IS NOT REGISTERED :( ----------------- \n");

	}
	
	// ------------------------- CHECKING VALIDATION -------------------------- //
	
		// -> CHECKING THE  VALIDATION OF BOTH " USER NAME " AND " PASSWORD "
	
	public String logValid () {
		
		// System.out.println("\n\t[ VALIDATING THE PASSWORD ....... ]\n");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.UserName(strUserName);                          //
		if(boolValid == false)                                                // --------------- validating the "userName" --------------- //
			return "USER NAME";                                              //
		
		boolValid = Validation.Password(strPassword);                          //
		if(boolValid == false)                                                // --------------- validating the "password" --------------- //
			return "PASSWORD";                                               //
		
		return strReturn;
	}
	
		// -> CHECKING THE  VALIDATION OF ONLY " USER NAME "
	
	public String logRemindValid () {
		
		// System.out.println("\n\t[ VALIDATING THE PASSWORD ....... ]\n");
		
		String strReturn = "true"; 
		boolean boolValid = true;
		
		boolValid = Validation.UserName(strUserName);                          //
		if(boolValid == false)                                                // --------------- validating the "userName" --------------- //
			return "USER NAME";                                              //
		
		return strReturn;
	}
	
	

}
