package controllers;
import mappers.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import io.vertx.core.http.*;

import models.*;
public class ViewsController {
	String result = "";
	public   void login()   throws Exception{
		String line = "";
			BufferedReader buffer = new BufferedReader(new FileReader("view/user/login.html"));
			while((line = buffer.readLine()) != null) {
				if(line.contains("<a href=#popup1>")) {
					result += line.replaceAll("<a href=#popup1>"," ");
					continue;
				}
				result += line;
			}
	}
	
	public  void signUp() throws Exception{
		String line = "";
			BufferedReader buffer = new BufferedReader(new FileReader("view/user/signup.html"));
			while((line = buffer.readLine()) != null) {
				if(line.contains("<a  href=#popup1>")) {
					result += line.replaceAll("<a  href=#popup1>"," ");
					continue;
				}
				result += line;
			}
	}

	public  void contactUs() throws Exception {
		String line = "";
		BufferedReader buffer = new BufferedReader(new FileReader("view/user/contact.html"));
		while((line = buffer.readLine()) != null) {
			if(User.flag == true) {
				if(line.contains("Guest")) {
					line = line.replace("Guest", User.username);
					line = line.replace("user.png","profile.png");
				}
				if(line.contains("id1")) {
					line = line.replace("id1", User.username);
				}
				if(line.contains("id2")) {
					line = line.replace("id2", User.email);
				}
				if(line.contains("3dx")) {
					result += line.replace("3dx", User.address);
				}
				if(!(line.contains("LogIn") ||  line.contains("SignUp"))) {
					result += line; 
				}
				if(line.contains("Home Page")) {
					result += "\n" + "<a href=\"/HistoriesController/loginHistory?file=history.html\">&nbsp;&nbsp;<img src=/AssetsController/image?file=history.png style=float:left;>Login History</a>";
					result += "\n" + "<a href=\"/UsersController/logout?file=logout\">&nbsp;&nbsp;<img src=/AssetsController/image?file=logout.png style=float:left;>Logout</a>";
					continue;
				}
			} else {
				
				if(line.contains("<a href=#popup1>")) {
					result += line.replaceAll("<a href=#popup1>"," ");
					continue;
				}
				result += line;
			
			}
		}
	}
	
	public void index(Program model) throws Exception{
		String line = "";
		BufferedReader buffer = null;
		System.out.println(User.address+"useraddressinviews");
			buffer = new BufferedReader(new FileReader("view/program/index.html"));
			while((line = buffer.readLine()) != null) {
				//System.out.println("uname ==> " + User.uName + "email==>" + User.email + "address==> " + User.address);
				if(User.flag == true) {
					//System.out.println("uname ==> " + User.uName + "email==>" + User.email + "address==> " + User.address);
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(line.contains("<h1>remaining content</h1>")) {
						result += line.replace("<h1>remaining content</h1>", (model.result + "</table>"));
						continue;
					}
					if(line.contains("id1")) {
						line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
					else {
						if(!(line.contains("LogIn") ||  line.contains("SignUp"))) {
							result += line; 
						}
						if(line.contains("Contact Us")) {
							result += "\n" + "<a href=\"/ProgramsController/add?file=add.html\">&nbsp;&nbsp;<img src=/AssetsController/image?file=add.png style=float:left;>Add Program</a>";							
							result += "\n" + "<a href=\"/HistoriesController/loginHistory?file=history.html\">&nbsp;&nbsp;<img src=/AssetsController/image?file=history.png style=float:left;>Login History</a>";
							result += "\n" + "<a href=\"/UsersController/logout?file=logout\">&nbsp;&nbsp;<img src=/AssetsController/image?file=logout.png style=float:left;>Logout</a>";
						}
						if(line.contains("Back")) {
							result += "\n" + "<a href=\"/UsersController/logout?file=logout\">&nbsp;&nbsp;<img src=/AssetsController/image?file=logout.png style=float:left;>Logout</a>";
						}
					}
				}
				else {
					if(line.contains("<a href=#popup1 >")) {
						result += line.replaceAll("<a href=#popup1 >"," ");
						continue;
					}
					if(line.contains("<h1>remaining content</h1>")) {
						result += model.result;
					}
					else {
						//if(!line.contains("<h1>remaining content</h1>"))
							result += line;
					}
				}
			}
	}
	
	public  void add() throws Exception {
		String line = "";
			BufferedReader buffer = new BufferedReader(new FileReader("view/program/add.html"));
			while((line = buffer.readLine()) != null) {
				if(User.flag == true) {
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(!(line.contains("LoginHistory") || line.contains("LogIn") || line.contains("SignUp") || line.contains("<h1>tag</h1>") || line.contains("<h1>tag1</h1>")))
						result += line;
					if(line.contains("id1")) {
						line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
				}
				else {
					if(line.contains("<a href=#popup1>")) {
						result += line.replaceAll("<a href=#popup1>"," ");
						continue;
					}
					if(!(line.contains("Login History") || line.contains("<h1>tag</h1>") || line.contains("<h1>tag1</h1>")) )
						result += line;
				}
			}
		result = result.replace("prt", "");
	}
	
	public  void show(Program model, String parameter, HttpServerRequest request) throws Exception {
		String line = "";
		int count = 0;
		String part = "";
		BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("execution/program.txt"));
		BufferedReader buffer = new BufferedReader(new FileReader("view/program/description.html"));
		BufferedReader reader = new BufferedReader(new  FileReader("execution/program.txt"));
		while((line = buffer.readLine()) != null) {
			if(User.flag == true) {
				if(line.contains("Guest")) {
					line = line.replace("Guest", User.username);
					line = line.replace("user.png","profile.png");
				}
				if(line.contains("Description")) {
					line = line.replace("Description", ProgramMapper.descriptionName);
				}
				if(line.contains("<h1 style=visibility: hidden>remaining content</h1>")) {
					bufferWriter.write(model.result);
					bufferWriter.flush();
					while((part = reader.readLine()) != null) {
						count++;
						if(count >= 1) {
							result += "<pre style = white-space:pre-wrap;>" + part + "</pre>";
							continue;
						}						
					}
				}		
				String url = request.getHeader("referer");
				if(url.contains("/ProgramsController/execute?")) {
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
					}
					System.out.println(url+"previousurlviewcontroller");
					String controller = url.split("[/]")[1];
					String action = url.split("[/]")[2].split("[?]")[0];
					String param = url.split("[?]")[1];
					System.out.println(param+"parameterviewcontroller");
					String input = param.split("[=]")[1];
					String compareInput = input.split("[&]")[0];
					System.out.println(compareInput);
					if(line.contains("id=input")) {
						result  += "<textarea id=input name = input  placeholder=inputvalue/>"+compareInput+"</textarea>";
						continue;
					}
					System.out.println(User.flag+"userflagview");
					System.out.println(input+"viewcontrollerinputvalue");
				}
				
				if(line.contains("id1")) {
					line = line.replace("id1", User.username);
				}
				if(line.contains("id2")) {
					line = line.replace("id2", User.email);
				}
				if(line.contains("3dx")) {
					result += line.replace("3dx", User.address);
					continue;
				}
				if(!(line.contains("LogIn") ||  line.contains("SignUp"))) {
					result += line; 
				}
				if(line.contains("Back")) {
					result += "\n" + "<a href=\"/HistoriesController/loginHistory?file=history.html\">&nbsp;&nbsp;<img src=/AssetsController/image?file=history.png style=float:left;>Login History</a>";					
					result += "\n" + "<a href=/HistoriesController/programHistory?file=program.html>&nbsp;&nbsp;<img src=/AssetsController/image?file=history.png style=float:left;>Program History</a>";
					result += "\n" + "<a href=\"/ProgramsController/getUpdate?file=add\">&nbsp;&nbsp;<img src=/AssetsController/image?file=update.png style=float:left;>Update Program</a>";
					result += "\n" + "<a href=\"/UsersController/logout?file=logout\">&nbsp;&nbsp;<img src=/AssetsController/image?file=logout.png style=float:left;>Logout</a>";
				}
			} else {
				if(line.contains("Description")) {
					line = line.replace("Description", parameter);
				}
				
				if(line.contains("<a href=#popup1>")) {
					result += line.replaceAll("<a href=#popup1>"," ");
					continue;
				}
				if(line.contains("<h1 style=visibility: hidden>remaining content</h1>")) {
					bufferWriter.write(model.result);
					bufferWriter.flush();
					while((part = reader.readLine()) != null) {
						count++;
						if(count >= 1) {
							result += "<pre style = white-space:pre-wrap;>" + part + "</pre>";
						}
					}
				} else {
					result += line;
				}
			}
		}
	}
	
	public String loginHistory(LoginHistory login) throws Exception{
		String line = "";
		String newLine = "";
			BufferedReader bufferReader = new BufferedReader(new FileReader("view/history/history.html"));
			System.out.println("mailer file " + MailersController.fileName);
			while((line = bufferReader.readLine()) != null) {
				if(User.flag == true) {
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(line.contains("remaining content")) {
						result += login.result; 
					}
					if(line.contains("id1")) {
						line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
					else {
						if(!line.contains("remaining content"))
							result += line;
					}
				}
				else {
					if(line.contains("<a href=#popup1>")) {
						result += line.replaceAll("<a href=#popup1>"," ");
						continue;
					}
					if(!line.contains("remaining content"))
						result += line;
				}
			}
		return result;
	}
	
	public String programHistory(ProgramHistory program) throws Exception {
		String line = "";
		String res = "";
			BufferedReader br = new BufferedReader(new FileReader("view/history/program.html"));
			while((line = br.readLine()) != null) {
				if(User.flag == true) {
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(line.contains("id1")) {
						line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
					if(line.contains("remaining items")) {
						res += program.result; 
					}
					else {
						if(!line.contains("remaining items"))
							res += line;
					}
				}
				else {
					if(line.contains("<a href=#popup1>")) {
						result += line.replaceAll("<a href=#popup1>"," ");
						continue;
					}
					if(!line.contains("remaining items"))
						res += line;
				}
			}
		return res;
	}
	
	public  void send(HttpServerRequest request) throws Exception{
		String line = "";
			BufferedReader buffer = new BufferedReader(new FileReader("view/program/send.html"));
			while((line = buffer.readLine()) != null) {
				if(User.flag == true) {
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(!(line.contains("LogIn"))) {
						result += line; 
					}
					if(line.contains("id1")) {
						line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
				}
				else {
					if(line.contains("<a href=#popup1>")) {
						result += line.replaceAll("<a href=#popup1>"," ");
						continue;
					}
					if(!line.contains("remaining items"))
					result += line;
				}
			}
		result = result.replace("your value", MailersController.fileName);
	}
	
	public String compare(ProgramsController program,Program model, String parameter) throws Exception {
		String line = "";
		int count = 0;
		String part = "";
		BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("execution/execution.txt"));
		BufferedReader buffer = new BufferedReader(new FileReader("view/program/description.html"));
		BufferedReader reader = new BufferedReader(new  FileReader("execution/execution.txt"));
		while((line = buffer.readLine()) != null) {
			if(User.flag == true) {
				if(line.contains("<h1>tag</h1>")) {
					result += "<tr><td>" + ProgramsController.programName + "</td><td><textarea id=compare readonly>" + ProgramsController.userInput + "</textarea></td><td><textarea id=compare readonly>" + ProgramsController.userOutput +  "</textarea></td><td><textarea id=compare readonly>" + program.programOutput +  "</textarea></td><td>";
					for(int index = 0; index <  ProgramsController.status.length; index++) {
						if(ProgramsController.status[index] == null)
							continue;
						else
							result += ProgramsController.status[index]+"</br>";
					}
					result += "</td></tr></table>";
					continue;
				}
				if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
						line = line.replace("user.png","profile.png");
					}
					if(line.contains("Description")) {
						line = line.replace("Description", ProgramMapper.descriptionName);
					}
					if(line.contains("<h1 style=visibility: hidden>remaining content</h1>")) {
						bufferWriter.write(model.result);
						bufferWriter.flush();
						bufferWriter.close();
						while((part = reader.readLine()) != null) {
							count++;
							System.out.println(count+"whileloopviewscontrollercompare");
							if(count >= 1) {
								System.out.println(part+"jyotiwhileloopviewscontrollercompare");
								result += "<pre style = white-space:pre-wrap;>" + part + "</pre>";
							}
						}
					}
					if(line.contains("<body>"))
						result += line.replace("<body>","<body onload=compare()>");
				else {			
					if(line.contains("id1")) {
							line = line.replace("id1", User.username);
					}
					if(line.contains("id2")) {
						line = line.replace("id2", User.email);
					}
					if(line.contains("3dx")) {
						result += line.replace("3dx", User.address);
					}
					if(line.contains("Guest")) {
						line = line.replace("Guest", User.username);
					}						
					result += line;
				}
			}
		}
		return result;
	}
}
