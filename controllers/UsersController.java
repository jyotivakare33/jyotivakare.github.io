package controllers;
import java.util.*;
import java.sql.*;
import io.vertx.core.http.*;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;  
import java.net.URLDecoder;
import java.io.*;

import models.*;
import lib.HttpParser;
public class UsersController {
	ViewsController viewController = new ViewsController();
	User userModel =  new User();
	Program programModel =  new Program();
	HttpParser parser = new HttpParser();
	//static String uName = "";
	
	public  void login(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		response.setStatusCode(200);
		response.headers().add("Content-Type","text/html");
		response.setChunked(true);
		viewController.login();
		response.write(viewController.result);	
	}
	
	public void signUp(Connection conn, HttpServerRequest request, HttpServerResponse response )  throws Exception{
		String user = request.getParam("name");
		if(user != null) {
			String email = request.getParam("email");
			String password = request.getParam("pwd");
			String confirmpassword = request.getParam("re-pwd");
			String address = request.getParam("address");
			ArrayList<String> list = new ArrayList<String>();
			list.add(user);
			list.add(email);
			list.add(password);
			list.add(confirmpassword);
			list.add(address);
			response.setStatusCode(200);
			response.setChunked(true);
			boolean flag = userModel.signUp(conn, list);
			if(flag) {
				programModel.index(conn);
				viewController.index(programModel);
				viewController.result += "<div class=success style=color:green>Signup Done</div>";
			}
			else {
				viewController.signUp();
			}
		} else 
			viewController.signUp();
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);
	}
	
	public void contactUs(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		String url = request.uri();
		viewController.contactUs();
		response.setStatusCode(200);
		response.headers().add("Content-Type","text/html");
		response.setChunked(true);
		response.write(viewController.result);	
	}
	
	public void validateLogin(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		String username = request.getParam("username");
		String password = request.getParam("password");
		ArrayList<String> list = new ArrayList<String>();
		list.add(username);
		list.add(password);
		response.setStatusCode(200);
		boolean flag = userModel.login(conn,list);
		if(flag) {
			
			String url = request.getHeader("referer");
			if(url.contains("/ProgramsController/execute?")) {
				System.out.println(url+"previousurl");
				String controller = url.split("[/]")[1];
				String action = url.split("[/]")[2].split("[?]")[0];
				String param = url.split("[?]")[1];
				System.out.println(param+"parameter");
				String input = param.split("[&]")[0];
				String output = param.split("[&]")[1];
				System.out.println(input+"input");
				System.out.println(ProgramsController.programName+"programname");			
				programModel.Execution(conn,request,ProgramsController.programName1);
				viewController.show(programModel, ProgramsController.programName,request);
				
				
			} else {
				programModel.index(conn);
				viewController.index(programModel);
				viewController.result += "<div class=success style=color:green>Login done</div>";
			}				
		}
		else {
			viewController.login();	
			viewController.result += "<div class=success style=color:red>Login failed</div>";
		}
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);	
	}
	
	public void contactAdmin(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
			String name =   URLDecoder.decode(request.getParam("name"),"UTF-8");
			String email =  URLDecoder.decode(request.getParam("email"), "UTF-8");
			String subject =  URLDecoder.decode(request.getParam("sub"), "UTF-8");
			String msg = URLDecoder.decode(request.getParam("msg"), "UTF-8");
			viewController.contactUs();
			Properties props = new Properties();    
			props.put("mail.smtp.host", "smtp.gmail.com");    
			props.put("mail.smtp.socketFactory.port", "465");    
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
			props.put("mail.smtp.auth", "true");    
			props.put("mail.smtp.port", "465");    
			Session session1 = Session.getDefaultInstance(props,    
		    new javax.mail.Authenticator() {    
				protected PasswordAuthentication getPasswordAuthentication() {    
					return new PasswordAuthentication("jyotivakare34@gmail.com" , "opara@1234");  
			   }    
			});     
			MimeMessage message = new MimeMessage(session1);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress("jyotivakare34@gmail.com"));    
			message.setSubject(subject);    
			message.setText(msg);     
			Transport.send(message);    
			MimeMessage msg1 = new MimeMessage(session1);    
			msg1.addRecipient(Message.RecipientType.TO,new InternetAddress(email));    
			msg1.setSubject(name + " Thank you for contacting us");    
			msg1.setText("we ll send notification as soon as possible");     
			Transport.send(msg1);    
			System.out.println("message sent successfully");  
		response.write(viewController.result+"<div class=success style=color:green>Mail Sent</div>"); 
	}
	
	public void logout(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception{
		User.flag = false;
		User.username = "Guest";
		System.out.println(User.username+"logout");
		programModel.index(conn);
		viewController.index(programModel);
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result+"<div class=success style=color:red>Logout Successfully</div>");	  
	}
	
	public void updateProfile(Connection connect,HttpServerRequest request,HttpServerResponse response)  throws Exception {								
		String result = "";
		String email = request.getParam("email");	
		String adress = request.getParam("address");	
		String name1 = request.getParam("username");
		if(name1 != null) {	
			boolean flag = userModel.updateProfile(connect,email,adress,name1);	
			if(flag) {
				System.out.println(flag);
				programModel.index(connect);
				viewController.index(programModel);
			}
		}
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result+"<div class=success style=color:green>Profile updated</div>");	  
	}
}
