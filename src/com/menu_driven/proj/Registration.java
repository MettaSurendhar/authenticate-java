package com.menu_driven.proj;

public class Registration {
	String strEmail;
	String strPassword;
	String strDob;
	String strUserName;
	String strInfo[] ;
	
	Registration ( String userName,String password,String email , String dob){
		this.strEmail = email;
		this.strPassword = password;
		this.strDob = dob;
		this.strUserName = userName;
	}
	
	public void registPost() {

		boolean boolResult  = DataBase.Insert(strUserName,strPassword,strEmail,strDob);
		
		if(boolResult)
			System.out.println(" YOU HAVE REGISTERED SUCCESSFULLY !!! ");
		else
			System.out.println(" YOUR REGISTRATION IS FAILED :( ");
	}
	
	public String registValid() {
		System.out.println("--- Validation ---");
		
		String strReturn = "true";
		boolean boolValid = true;
		
		boolValid = Validation.Email(strEmail);
		if(boolValid == false)
			return "EMAIL";
		
		boolValid = Validation.Dob(strDob);
		if(boolValid == false)
			return "DATE OF BIRTH";
		
		boolValid = Validation.UserName(strUserName);
		if(boolValid == false)
			return "USER NAME";
		
		boolValid = Validation.Password(strPassword);
		if(boolValid == false)
			return "PASSWORD";
		
		
		return strReturn;
		
	}
	
}
