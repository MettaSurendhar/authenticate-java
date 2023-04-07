package com.menu_driven.proj;


import java.util.Scanner;

public class View {
	
	static String strUserName;
	static String strEmail;
	static String strPassword ;
	static String strDob;

	public static void main(String[] args){
		
		@SuppressWarnings("resource")
		Scanner  in = new Scanner (System.in);
		
		System.out.println(" -------------------------------------------- \n\n SELECT AN OPTION FOR AUTHENTICATION : \n\n  -> 1. USER-REGISTRATION \n\n  -> 2. USER - LOGIN \n\n -------------------------------------------- \n\n");
		System.out.println(" => ENTER AN OPTION FROM THE ABOVE MENTIONED (either 1 or 2) : ");
		int val = 0 ;
		try {
			val = in.nextInt();
		}
		catch (Exception e) {
			System.out.println("ENTER A VALID OPTION 1 0R 2 \n");
			return;
			
		}
		
		
		
		switch (val) {
		
			case 1: 
				
				//  ----------------- REGISTRATION --------------------- //
				
				System.out.println("\n --------------- REGISTRATION --------------- \n");
				
				Registration objReg ;
				String strRegValid = "false";
				
				do
				{
					strEmail = in.nextLine();  // ---- temporary variable for resolving bug ---- //
					
					System.out.println("Enter EMAIL ID :");
					strEmail = in.nextLine();
					System.out.println("Enter DATE OF BIRTH : ");
					strDob = in.nextLine();
					System.out.println("Enter USER NAME : ");
					strUserName = in.nextLine();
					System.out.println("Enter PASSWORD :");
					strPassword = in.nextLine();
					objReg = new Registration(strUserName,strPassword,strEmail,strDob);
					strRegValid = objReg.registValid();
					
					if (!strRegValid.equals("true")) {
						regNonValid(strRegValid);
						strRegValid = "false";
					}
					
				}
				while(strRegValid.equals("false"));
				
				objReg.registPost();
				break;
			case 2 :
				
				//  ----------------- LOGIN --------------------- //
				
				System.out.println("\n ---------------- LOGIN ----------------- \n");
				
				Login objLog;
				String strLogValid = "false";
				
				do {
					strUserName = in.nextLine();
					System.out.println("Enter USER NAME : ");
					strUserName = in.nextLine();
					System.out.println("Enter PASSWORD :");
					strPassword = in.nextLine();
					objLog = new Login(strUserName, strPassword );
					strLogValid = objLog.logValid();
					
					if (!strLogValid.equals("true")) {
						logNonValid(strLogValid);
						strLogValid = "false";
					}
					
				}
				while(strLogValid.equals("false"));
				
				objLog.logGet();
				break;
				
			default :
				System.out.println("ENTER A VALID OPTION 1 0R 2");
				break;
			
			}
		
		return;
		
		}
	
	//  ----------- PRINT IF ENTERED VALUE IS NOT VALID IN "REGISTRATION" ------------- //
	
	public static void regNonValid(String strNonValid) {
		
		System.out.println(" ------ ENTERED INVALID VALUE IN " + strNonValid + " FIELD ------ " );
		
		if(strNonValid.equals("EMAIL"))
			System.out.println("\n  -> Mail_id must have \"@\" , domain and higher domain mentioned and should not have any special characters except underscore and period ");
		
		else if(strNonValid.equals("DATE OF BIRTH"))
			System.out.println("\n  -> Date_of_Birth must be in the form of \'dd/mm/yyyy\' or \'dd-mm-yyyy\' .");
		
		else if(strNonValid.equals("USER NAME"))
			System.out.println("\n  -> User_Name should not have any special characters except underscore and can have uppercase letters (case sensitive) and must be >7 characters.");
		
		else if(strNonValid.equals("PASSWORD"))
			System.out.println("\n  -> Password must have an special character , number , uppercase and lowercase letters and must be >7 characters.");
		
		System.out.println("\n ------ CLICK - \'ENTER\' TO CONTINUE ------ ");

		
	}
	
	//  -----------------  PRINT IF ENTERED VALUE IS NOT VALID IN "LOGIN"  --------------- //
	
	public static void logNonValid(String strNonValid) {
			
			System.out.println(" ------ ENTERED INVALID VALUE IN " + strNonValid + " FIELD ------ " );
			
			if(strNonValid.equals("USER NAME"))
				System.out.println("\n  -> User_Name should not have any special characters except underscore and can have uppercase letters (case sensitive) and must be >7 characters.");
			
			else if(strNonValid.equals("PASSWORD"))
				System.out.println("\n  -> Password must have an special character , number , uppercase and lowercase letters and must be >7 characters.");
			
			System.out.println("\n ------ CLICK - \'ENTER\' TO CONTINUE ------ ");
	
			
		}
	
}
