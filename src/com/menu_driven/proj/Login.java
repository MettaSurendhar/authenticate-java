package com.menu_driven.proj;

public class Login {
	
	String strUserName;
	String strPassword;
	boolean boolResult;
	
	Login(String userName,String password){
		this.strUserName = userName;
		this.strPassword = password;
	}
	
	public void logGet () {
		
		boolResult = DataBase.Select(strUserName,strPassword);
		
		if(boolResult)
			System.out.println(" YOU HAVE LOGED IN SUCCESSFULLY !!! ");
		else
			System.out.println(" ENTERED USERNAME OR PASSWORD IS INVALID :( ");

	}
	
	public String logValid () {
		
		System.out.println("--- Validation ---");
		
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
