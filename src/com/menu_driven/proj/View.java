package com.menu_driven.proj;


import java.util.Scanner;

public class View {
	
	static String strUserName;
	static String strEmail;
	static String strPassword ;
	static String strNewPassword;
	static String strDob;
	static boolean boolRemindMe = false ;

	public static void main(String[] args){
		
		DataBase.Connect();
		
		@SuppressWarnings("resource")
		Scanner  in = new Scanner (System.in);
		boolean exit = false;
		
		System.out.println(" -------------------------------------------------------------------- "
				+ "\n\n SELECT AN OPTION FOR AUTHENTICATION : \n\n  -> 1. USER - REGISTRATION \n\n  -> 2. USER - LOGIN \n\n  -> 3. REMIND ME \n\n  -> 4. RESET PASSWORD \n\n  -> 5. CLOSE \n\n "
				+ "-------------------------------------------------------------------- ");

		
		while(exit != true) {
			System.out.println("\n -------------------------------------------------------------------- \n"
					+ "\n => ENTER AN OPTION FROM THE ABOVE MENTIONED MENU DRIVEN\n    (register before login) : ");
			int val = 0 ;
			try {
				val = in.nextInt();
			}
			catch (Exception e) {
				System.out.println(" => ENTER A VALID OPTION BETWEEN 1 AND 4\n");
				return;
				
			}
			
			
			
			switch (val) {
			 
				case 1: 
					
					//  ------------------------------- REGISTRATION -------------------------------- //
					
					System.out.println("\n -----------------------------------------------\n"
							+ "\t\t USER - REGISTRATION "
							+ "\n -----------------------------------------------\n");
					
					Registration objReg ;
					String strRegValid = "false";
					int regRepeat = 3;
					
					
					do
					{
						strEmail = in.nextLine();                // --------- temporary variable for resolving bug ----------- //
						
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
						regRepeat--;
						
						if (!strRegValid.equals("true")) {
							regNonValid(strRegValid);
							strRegValid = "false";
						}
						
					}
					while(strRegValid.equals("false")&& regRepeat>0);
					
					if(regRepeat == 0) {
						System.out.println("\n ************ | ENTERED WRONG VALUES CONTINU0USLY , TRY AGAIN TO REGISTER | ************ \n");
						break;
					}
					
					objReg.registPost();
					if(boolRemindMe != true) {
						System.out.println("\n (( TURN ON THE REMIND ME (OPTION 3) IF NEEDED )) \n");
					}
					break;
					
				case 2 :
					
					//  ------------------------------- LOGIN ------------------------------- //
					
					System.out.println("\n -----------------------------------------------\n"
							+ "\t\t USER - LOGIN "
							+ "\n -----------------------------------------------\n");
					
					Login objLog;
					String strLogValid = "false";
					int logRepeat;
					
					if (boolRemindMe == true) {
						
							// -> LOGGING IN BY REMINDING THE PASSWORD
						
						logRepeat = 3;
						
						do {
							strUserName = in.nextLine();
							System.out.println("Enter USER NAME : ");
							strUserName = in.nextLine();
							objLog = new Login(strUserName);
							strLogValid = objLog.logRemindValid();
							logRepeat-- ;
							
							if (!strLogValid.equals("true")) {
								logNonValid(strLogValid);
								strLogValid = "false";
							}
							
						}
						while(strLogValid.equals("false") && logRepeat>0);
						
						objLog.logRemindGet();
						
					}
					else {
						
							// -> LOGGING IN BY NOT REMINDING THE PASSWORD
						
						logRepeat =  3;
						
						do {
							strUserName = in.nextLine();
							System.out.println("Enter USER NAME : ");
							strUserName = in.nextLine();
							System.out.println("Enter PASSWORD :");
							strPassword = in.nextLine();
							objLog = new Login(strUserName, strPassword );
							strLogValid = objLog.logValid();
							logRepeat-- ;
							
							if (!strLogValid.equals("true")) {
								logNonValid(strLogValid);
								strLogValid = "false";
							}
							
						}
						while(strLogValid.equals("false")&& logRepeat>0);
						
						if(logRepeat == 0) {
							System.out.println("\n ************ | ENTERED WRONG VALUES CONTINU0USLY , TRY AGAIN TO REGISTER | ************ \n");
							break;
						}
						
						objLog.logGet();
						if(boolRemindMe != true) {
							System.out.println("\n (( TURN ON THE REMIND ME (OPTION 3) IF NEEDED )) \n");
						}
					}
					
					break;
					
				case 3:
					
					//  ------------------------------- REMIND ME ------------------------------- //
					
					System.out.println("\n -----------------------------------------------\n"
							+ "\t\t REMIND ME "
							+ "\n -----------------------------------------------\n");
					String strRemindMe= in.nextLine();
					System.out.println(" -> WOULD YOU WANT TO REMEMBER YOUR PASSWORD (yes or no) ? ");
					strRemindMe= in.nextLine();
					RemindMe(strRemindMe);
					
					break;
					
				case 4:
						
					//  ----------------------------- RESET PASSWORD ----------------------------- //
					
					System.out.println("\n -----------------------------------------------\n"
							+ "\t\t RESET PASSWORD "
							+ "\n -----------------------------------------------\n");
					
					ResetPassword objReset;
					String strResetLogValid = "false";
					int resetRepeat = 3;
					
					do {
						strUserName = in.nextLine();
						System.out.println("Enter USER NAME : ");
						strUserName = in.nextLine();
						System.out.println("Enter PASSWORD :");
						strPassword = in.nextLine();
						objReset = new ResetPassword(strUserName, strPassword );
						strResetLogValid = objReset.resetLogValid();
						resetRepeat-- ;
						
						if (!strResetLogValid.equals("true")) {
							logNonValid(strResetLogValid);
							strResetLogValid = "false";
						}
						
					}
					while(strResetLogValid.equals("false")&& resetRepeat>0);
					
					if(resetRepeat == 0) {
						System.out.println("\n ************ | ENTERED WRONG VALUES CONTINU0USLY , TRY AGAIN TO REGISTER | ************ \n");
						break;
					}
					
					System.out.println("\n -------------------- AN 'OTP' WILL BE SENT TO YOUR EMAIL TO RESET PASSWORD -------------------- \n");

					int iOtp = objReset.resetOtp();
					int iEnteredOtp = 0;
					int chance = 3;
					boolean flag = false; 
					int otpRepeat;
					
					while(chance >0) {
						System.out.println("Enter The OTP : ");
						iEnteredOtp = in.nextInt();
						 
						 if(iEnteredOtp == iOtp) {

							 strResetLogValid = "false";
							 otpRepeat = 3;
							 
							 do {
								 	strNewPassword = in.nextLine();
									System.out.println("Enter THE NEW PASSWORD :");
									strNewPassword = in.nextLine();
									strResetLogValid = objReset.resetLogValid(strNewPassword);
									otpRepeat-- ;
									
									if (!strResetLogValid.equals("true")) {
										logNonValid(strResetLogValid);
										strResetLogValid = "false";
									}
									
								}
								while(strResetLogValid.equals("false")&& otpRepeat>0);
							 flag = true;
							 break;
						 }
						 else {
							 
							 chance--;
							 if(chance>0) {
								 System.out.println("\n ************ | PLEASE ENTER THE CORRECT OTP RECEIVED TO YOUR MAIL | ************");
								 System.out.println("\n -> CHANCES LEFT => " + chance +"\n");
							 }
							 else {
								 System.out.println("\n ************ | NO CHANCES LEFT :( -> RESTART AGAIN TO RESET PASSWORD | ************ \n");
							 }
						 }
						
					}
					
					
					
					if(!flag) {
						System.out.println("------------------ RESET PASSWORD IS NOT POSSIBLE :( ----------------");
					}
					else {
						objReset.resetPost(strNewPassword);
					}
					
					break;
					
				case 5:
					
					//  ----------------------------- EXIT THE LOOP ----------------------------- //
					
					System.out.println("\n\t[ CLOSING THE MENU DRIVEN ........... ] \n");
					
					exit = true;
					break;
					
				default :
					System.out.println("\n ************ | ENTER A VALID OPTION BETWEEN 1 AND 4 | ************ \n");
					break;
				
				}
			
		}
		
		
		DataBase.Close();
		return;
		
		}
	
	//  ------------------------------ PRINT IF ENTERED VALUE IS NOT VALID IN "REGISTRATION" ------------------------------ //
	
	public static void regNonValid(String strNonValid) {
		
		System.out.println(" ************ | ENTERED INVALID VALUE IN " + strNonValid + " FIELD | ************ " );
		
		if(strNonValid.equals("EMAIL"))
			System.out.println("\n  -> Mail_id must have \"@\" , domain and higher domain mentioned and should not have any special characters except underscore and period ");
		
		else if(strNonValid.equals("DATE OF BIRTH"))
			System.out.println("\n  -> Date_of_Birth must be in the form of \'dd/mm/yyyy\' or \'dd-mm-yyyy\' .");
		
		else if(strNonValid.equals("USER NAME"))
			System.out.println("\n  -> User_Name should not have any special characters except underscore and can have uppercase letters (case sensitive) and must be >7 characters.");
		
		else if(strNonValid.equals("PASSWORD"))
			System.out.println("\n  -> Password must have an special character , number , uppercase and lowercase letters and must be >7 characters.");
		
		System.out.println("\n ----------------- CLICK - \'ENTER\' TO CONTINUE ----------------- ");

		
	}
	
	//  --------------------------------  PRINT IF ENTERED VALUE IS NOT VALID IN "LOGIN"  --------------------------------- //
	
	public static void logNonValid(String strNonValid) {
			
			System.out.println(" ************ | ENTERED INVALID VALUE IN " + strNonValid + " FIELD | ************ " );
			
			if(strNonValid.equals("USER NAME"))
				System.out.println("\n  -> User_Name should not have any special characters except underscore and can have uppercase letters (case sensitive) and must be >7 characters.");
			
			else if(strNonValid.equals("PASSWORD"))
				System.out.println("\n  -> Password must have an special character , number , uppercase and lowercase letters and must be >7 characters.");
			
			System.out.println("\n ----------------- CLICK - \'ENTER\' TO CONTINUE ----------------- ");
	
			
		}
	
//  ----------------------------  ACTIVATE THE REMIND ME IF USER ENTERED "YES" OTHERWISE DONT  ---------------------------- //
	
	public static void RemindMe(String strValue) {
		
		String strRemindMe = strValue.toLowerCase();
		
		if(strRemindMe.equals("y") || strRemindMe.equals("ye") || strRemindMe.equals("yes")) 
			boolRemindMe = true;
		else if(strRemindMe.equals("n") || strRemindMe.equals("no") )
			boolRemindMe = false;
		else {
			System.out.println("\n ************ | PLEASE ENTER CORRECT VALUE | ************ \n");
			return;
		}
		
		System.out.println("\n ---------------- YOUR PASSWORD IS REMINDED , NOW YOU CAN LOGIN WITHOUT PASSWORD :) ------------- \n");
	}
	
}
