package com.menu_driven.proj;

import java.util.Date;
import java.util.Properties;

//import javax.mail.Session;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	static int iOtp;
	static final String strFromMailId = "msurendhar8815@gmail.com";
	static final String strAppPassword = "zkxhqcyncmyzlsbd";
	static final String strSmtpHostServer = "smtp.gmail.com";
	static String strToEmail ;
	
	
	
	public static void sendMail(String toEmail,int otp) {
		
		strToEmail = toEmail;
		iOtp = otp;
		
		
		try {
			Properties objProps = System.getProperties();
			objProps.put("mail.smtp.host", strSmtpHostServer);
			objProps.put("mail.smtp.starttls.enable", true);
			objProps.put("mail.smtp.auth", "true"); 
			objProps.put("mail.smtp.port", "587");
			
			Session objSession = Session.getInstance(objProps, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(strFromMailId, strAppPassword);
				}
			});
			
			System.out.println("\n\t[ PREPARING MAIL TO SEND OTP ....... ] \n");
			
			
			MimeMessage msg = new MimeMessage(objSession);

			msg.setFrom(new InternetAddress(strFromMailId));
			msg.setSubject("OTP verification for JDBC authentication");
			msg.setText("Enter this OTP to generate new password\n\n OTP = " + iOtp);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strToEmail, false));
			System.out.println("\n\t[ MESSAGE IS READY TO SEND ....... ] \n");
			Transport.send(msg);
			System.out.println("\n ------------ EMAIL SENT SUCCESSFULLY :) ------------- \n");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
