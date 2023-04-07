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
	static String strEncryptedPassword;
	static String strPassword;
	static String strDob;
	static String strUserName;
	
	// --------------- ACCESSING THE DATABASE TO INSERT DATA ---------------------- //
	
	public static boolean Insert (String userName , String encryptedPassword , String email , String dob,String password) {
		
		DataBase.strUserName = userName ;
		DataBase.strEncryptedPassword = encryptedPassword;
		DataBase.strPassword = password;
		DataBase.strEmail = email;
		DataBase.strDob = dob;
		DataBase.strPassword = password;
		boolean registered = false;
		
		       
		
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","Suren@19_2004");    // ------------ connecting to the "MySql" database------------ //
			
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
				con.close();
			}
			catch(Exception e){
				System.out.println("Error : " + e);
			}
			
		}
		return registered ;
		
	}
	
	// --------------- ACCESSING DATABASE TO SELECT DATA  ---------------------- //
	
	public static boolean Select (String userName , String encryptedPassword) {
		
		DataBase.strUserName = userName;
		DataBase.strEncryptedPassword = encryptedPassword;
		boolean loged = false;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","Suren@19_2004");              // ------------ connecting to the "MySql" database------------ //
			
			stmt = con.prepareStatement("select userName,encryptedPassword from user_details where userName = ?");    // ------------ writing select query in prepared statement ------------ //
			
			stmt.setString(1,strUserName);                                   // ------------ setting the required values in prepared statement ------------ // 
			
			rs = stmt.executeQuery();                                      // ------------ execute the query to the database ------------ //
			
			while(rs.next()) {
				
				String strGet1 = rs.getString(1);                                 //
				                                                                 // ------------ getting the required values in prepared statement ------------ // 
				String strGet2 = rs.getString(2);                               //
	
				if((strGet1 + strGet2).equals((strUserName + strEncryptedPassword)))            // ----------- checking whether the password entered is correct or not ---------- //
					loged = true;
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		finally {
			try {
				stmt.close();
				con.close();
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return loged;
	}
	

}
