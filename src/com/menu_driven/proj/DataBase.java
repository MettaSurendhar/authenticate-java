package com.menu_driven.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBase {
	
	static Connection con ;
	static PreparedStatement stmt ;
	static ResultSet rs ;
	static String strEmail;
	static String strDob;
	static String strUserName;
	static String strPassword;
	static String strEncryptedPassword;
	static String strResetPassword;
	
	
	// -------------------------- CONNECTING TO THE "MYSQL" => "JAVA" DATABASE ------------------------------- //
	
	public static void Connect() {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","Suren@19_2004");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void Close() {
		try {
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	// -------------------------- ACCESSING THE DATABASE TO INSERT DATA ------------------------------- //
	
	public static boolean Insert (String userName , String encryptedPassword , String email , String dob,String password) {
		
		DataBase.strUserName = userName ;
		DataBase.strEncryptedPassword = encryptedPassword;
		DataBase.strPassword = password;
		DataBase.strEmail = email;
		DataBase.strDob = dob;
		DataBase.strPassword = password;
		boolean registered = false;
		
		       
		
		try {
			
			    // ------------ connecting to the "MySql" database------------ //
			
			stmt = con.prepareStatement("insert into user_details values(?,?,?,?,?)");      // ------------ writing insert query in prepared statement ------------ //
			
			stmt.setString(1,strUserName);                           //
			stmt.setString(2, strEncryptedPassword);                //
			stmt.setString(3, strEmail);                           //      ------------ setting the required values in prepared statement ------------  //
			stmt.setString(4,strDob);                             //
			stmt.setString(5, strPassword);                      // 
			
			stmt.executeUpdate();                  // ------------ execute the update to the database ------------ //
			registered = true;

		}
		catch(SQLException e){
			
			System.out.println("Error : " + e.getMessage());
		}
		finally {
			try {
				stmt.close();
				
			}
			catch(Exception e){
				System.out.println("Error : " + e);
			}
			
		}
		return registered ;
		
	}
	
	
	
	// ------------------------- ACCESSING THE DATABASE TO INSERT DATA ------------------------------ //
	
	public static boolean Update (String userName , String encryptedPassword , String password) {
			
		DataBase.strUserName = userName ;
		DataBase.strResetPassword = encryptedPassword;
		DataBase.strPassword = password;

		boolean registered = false;      
		
		try {
			
			 // ------------ connecting to the "MySql" database------------ //
			
			stmt = con.prepareStatement(" update user_details set encryptedPassword=? , password=? where userName=? ");      // ------------ writing update query in prepared statement ------------ //
			
			stmt.setString(1, strResetPassword);                  //
			stmt.setString(2, strPassword);                      //       ------------ setting the required values in prepared statement ------------  //                                     
			stmt.setString(3, strUserName);                         //
			
			stmt.executeUpdate();                  // ------------ execute the update to the database ------------ //
			registered = true;

		}
		catch(SQLException e){
			
			System.out.println("Error : " + e.getMessage());
		}
		finally {
			try {
				stmt.close();
				
			}
			catch(Exception e){
				System.out.println("Error : " + e);
			}
			
		}
		return registered ;
		
	}
		
	// -------------------------- ACCESSING DATABASE TO SELECT ENCRYPTED PASSWORD  ------------------------------ //
	
	public static boolean SelectEncrypt(String userName , String encryptedPassword) {
		
		DataBase.strUserName = userName;
		DataBase.strEncryptedPassword = encryptedPassword;
		boolean loged = false;
		
		try {
			 // ------------ connecting to the "MySql" database------------ //
			
			stmt = con.prepareStatement("select encryptedPassword from user_details where userName = ?");    // ------------ writing select query to select encryptedPassword ------------ //
			
			stmt.setString(1,strUserName);                                   // ------------ setting the required values in prepared statement ------------ // 
			
			rs = stmt.executeQuery();                                      // ------------ execute the query to the database ------------ //
			
			while(rs.next()) {
				
				String strGet = rs.getString(1);                               // ------------ getting the required values in prepared statement ------------ //
	
				if((strGet).equals((strEncryptedPassword)))            // ----------- checking whether the password entered is correct or not ---------- //
					loged = true;
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		finally {
			try {
				stmt.close();
				
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return loged;
	}
		
	// --------------------------- ACCESSING DATABASE TO SELECT EMAIL  ------------------------------ //
	
	public static String SelectEmail (String userName ) {
		
		DataBase.strUserName = userName;

		String strToEmail = null;
		
		try {
			 // ------------ connecting to the "MySql" database------------ //
			
			stmt = con.prepareStatement("select email from user_details where userName = ?");    // ------------ writing select query to select email ------------ //
			
			stmt.setString(1,strUserName);                                   // ------------ setting the required values in prepared statement ------------ // 
			
			rs = stmt.executeQuery();                                      // ------------ execute the query to the database ------------ //
			
			while(rs.next()) {
				
				strToEmail = rs.getString(1);                               // ------------ getting the required values in prepared statement ------------ //
	
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		finally {
			try {
				stmt.close();

			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return strToEmail;
	}
	
	// -------------------------- ACCESSING DATABASE TO SELECT ENCRYPTED PASSWORD  ------------------------------ //
	
	public static boolean SelectEncrypt(String userName ) {
				
			DataBase.strUserName = userName;
			
			boolean loged = false;
			
			try {
				  // ------------ connecting to the "MySql" database------------ //
				
				stmt = con.prepareStatement("select userName from user_details where userName = ?");    // ------------ writing select query to select encryptedPassword ------------ //
				
				stmt.setString(1,strUserName);                                   // ------------ setting the required values in prepared statement ------------ // 
				
				rs = stmt.executeQuery();                                      // ------------ execute the query to the database ------------ //
				
				while(rs.next()) {
					
					String strGet = rs.getString(1);                               // ------------ getting the required values in prepared statement ------------ //
		
					if((strGet).equals((strUserName)))            // ----------- checking whether the password entered is correct or not ---------- //
						loged = true;
				}
				
			}
			catch(SQLException e) {
//					System.out.println("Error : " + e.getMessage());
				loged = false;
			}
			finally {
				try {
					stmt.close();
					
					return loged;
				}
				catch(Exception e) {
					System.out.println("Error: " + e);
				}
			}
			return loged;
		}
	

}
