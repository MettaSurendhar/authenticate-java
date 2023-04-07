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
	static String strPassword;
	static String strDob;
	static String strUserName;
	
	public static boolean Insert (String userName , String password , String email , String dob) {
		
		DataBase.strUserName = userName ;
		DataBase.strPassword = password;
		DataBase.strEmail = email;
		DataBase.strDob = dob;
		boolean registered = false;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","Suren@19_2004");
			stmt = con.prepareStatement("insert into user_details values(?,?,?,?)");
			stmt.setString(1,strUserName);
			stmt.setString(2, strPassword);
			stmt.setString(3, strEmail);
			stmt.setString(4,strDob);
			stmt.executeUpdate();
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
	
	public static boolean Select (String userName , String password) {
		
		DataBase.strUserName = userName;
		DataBase.strPassword = password;
		boolean loged = false;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","Suren@19_2004");
			stmt = con.prepareStatement("select userName,password from user_details where userName = ? and password = ?");
			stmt.setString(1,strUserName);
			stmt.setString(2, strPassword);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String strRs1 = rs.getString(1);
				String strRs2 = rs.getString(2);
	
				if((strRs1+strRs2).equals((strUserName+strPassword)))
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
