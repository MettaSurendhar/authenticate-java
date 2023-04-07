package com.menu_driven.proj;

//import java.io.Console;
import java.util.Scanner;

public class View {
	
	static String strUserName;
	static String strEmail;
	static String strPassword ;
	static String strDob;
//	static Console console = System.console();

	public static void main(String[] args){
		
		@SuppressWarnings("resource")
		Scanner  in = new Scanner (System.in);
		
		System.out.println("The given options : \n 1. User Registration \n 2. User Login ");
		System.out.println("Enter an option (either 1 or 2) : ");
		int val = 0 ;
		try {
			val = in.nextInt();
		}
		catch (Exception e) {
			System.out.println("ENTER A VLID OPTION 1 0R 2");
			return;
			
		}
		
		
		
		switch (val) {
		
			case 1: 
	//			System.out.println("REGISTRATION");
				
				///// ------------ REGISTRATION --------------- /////////////
				Registration objReg ;
				String strRegValid = "false";
				do
				{
					strEmail = in.nextLine();
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
						System.out.println("ENTERED INVALID VALUE IN " + strRegValid + " FIELD \n \" CLICK - \'ENTER\' TO CONTINUE \" ");
						strRegValid = "false";
					}
				}
				while(strRegValid.equals("false"));
				
				objReg.registPost();
				break;
			case 2 :
	//			System.out.println("LOGIN");
				
				///// ------------ LOGIN --------------- /////////////
	
				Login objLog;
				String strLogValid = "false";
				do {
					strUserName = in.nextLine();
					System.out.println("Enter USER NAME : ");
					strUserName = in.nextLine();
					System.out.println("Enter PASSWORD :");
					strPassword = in.nextLine();
//					strEncr = Encription.encrypt(strPassword);
					objLog = new Login(strUserName, strPassword );
					strLogValid = objLog.logValid();
					
					if (!strLogValid.equals("true")) {
						System.out.println("ENTERED INVALID VALUE IN " + strLogValid + " FIELD \n \" CLICK - \'ENTER\' TO CONTINUE \" ");
						strLogValid = "false";
					}
				}
				while(strLogValid.equals("false"));
				
				objLog.logGet();
				break;
			
			}
		
		return;
		
		}
	}
