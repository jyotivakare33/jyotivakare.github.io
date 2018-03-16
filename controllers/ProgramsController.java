package controllers;
import models.*;
import io.vertx.core.http.*;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;

import models.*;
public class ProgramsController {
    ViewsController  viewController = new ViewsController();
	Program programModel = new Program();
	ProgramHistory programHistories = new ProgramHistory();
	public static String programName = "";
	public static String programName1 = "";
	static String output ="";
	String pName = "";
	String srcCode = "";
	String description = "";
	String code = "";
	String desc = "";
	public static String userInput = "";
	public static String userOutput = "";
	public String programOutput = "";
	public static Boolean[] status = new Boolean[6];
	
	public void index(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		programModel.index(conn);
		viewController.index(programModel);
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);	
	}
	
	public void add(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception{
		viewController.add();
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);	
	}
	
	public void addProgram(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		pName = request.getParam("pname");
		description = request.getParam("input");
		srcCode= request.getParam("output");
		File file = new File("java/" + pName + ".java");
		BufferedWriter buffer = null;
		boolean flag = programModel.add(conn,request);
		buffer = new BufferedWriter(new FileWriter(file));
		buffer.write(srcCode);
		buffer.flush();
		if(flag) {
			programModel.index(conn);
			viewController.index(programModel);
		}	
		else {
			viewController.add();
			viewController.result += "<div class=success style=color:green>program added</div>";
		}
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);	
	}
	
	public void show(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		programName =  request.getParam("file");
		programName1 =  request.getParam("file");
		programModel.show(conn,request);
		viewController.show(programModel, programName,request);
		output = viewController.result;
		response.headers().add("Content-Length", String.valueOf(output.length())).add("Content-Type", "text/html");
		response.write(output);			
	}
	
	public void execute(Connection con, HttpServerRequest request, HttpServerResponse response) throws Exception {
		System.out.println("program name =" + programName);
		String url = request.uri();
		String result = "";
		String action = request.getParam("action");
		System.out.println("action is ==>" + action);
		if(action.equals("EXECUTE")) {
			if(User.flag == true) {
				System.out.println(User.username+"usernameinprogramsexecute");	
				String line = "";
				String parameter = url.substring(url.indexOf("=") + 1, url.indexOf("&"));
				String input = request.getParam("input");
				BufferedWriter writer = new BufferedWriter(new FileWriter("execution/input.txt"));
				writer.write(input);
				writer.flush();
				Process firstProcess = Runtime.getRuntime().exec("javac " + "java/"+ programName + ".java");
				Process secondProcess = Runtime.getRuntime().exec("java " + "-cp java " + programName + " " + "execution/input.txt");
				BufferedReader buffer = new BufferedReader(new InputStreamReader(secondProcess.getInputStream()));
				while ((line = buffer.readLine()) != null) {
					result += line + "\\n";
					System.out.println("ans is " + line);
				}
				parameter =  parameter .replaceAll("%0D%0A","\\\\n");
				parameter = URLDecoder.decode( parameter , "UTF-8");
				output = output.replace("Guest", User.username);
				String[] values = output.split("</body>");
				line = "";
				line = line + "<script>";
				line = line + "document.getElementById(\"input\").innerHTML = " +"\"" + parameter + "\"" +"\n";
				line = line + "document.getElementById(\"output\").innerHTML = " + "\"" + result + "\"" +"\n";
				line = line + "document.getElementById(\"input\").focus()";
				line = line +"</script>";
				
				line = line +"</body>";
				
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("execution/execute.txt"));
				bufferedWriter.write(result);
				bufferedWriter.flush();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter("execution/output.txt"));
				bWriter.write(result);
				bWriter.flush();
				response.write((values[0] + line + values[1]));	
				programHistories.executeQuery(con);	
				System.out.println("SFRFSDSGFGETGEGER");	
			}
			else {
				System.out.println("execution without login");
				viewController.login();
				response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
				response.write(viewController.result);
				
			}
		}
		if(action.equals("COMPARE")) {
			if(User.flag == true) {
				String line="";
				String expectedOutput  ="";
				userInput = request.getParam("input");
				userOutput = request.getParam("output");
				String parameter = url.substring(url.indexOf("=") + 1, url.indexOf("&"));
				String input = request.getParam("input");
				BufferedWriter writer2 = new BufferedWriter(new FileWriter("execution/input.txt"));
				writer2.write(input);
				writer2.flush();
				Process firstProcess = Runtime.getRuntime().exec("javac " + "java/"+ programName + ".java");
				Process secondProcess = Runtime.getRuntime().exec("java " + "-cp java " + programName + " " + "execution/input.txt");
				BufferedReader buffer = new BufferedReader(new InputStreamReader(secondProcess.getInputStream()));
				while ((line = buffer.readLine()) != null) {
					programOutput += line + "\\n";
					System.out.println("ans is " + line);
				}
				programOutput = programOutput.replaceAll("\\\\n", "<br>");
				
				String[] total = programOutput.split("<br>");
				PrintWriter writer1 = new PrintWriter("execution/programOut.txt", "UTF-8");
				for(int index = 0; index < total.length; index++) {						
					writer1.write(total[index]);
					writer1.write("\n");
				}
				//writer1.flush();
				writer1.close();
				String[] pOut = new String[total.length];
				//programOutput = "";
				BufferedReader reader1 = new BufferedReader(new FileReader("execution/programOut.txt"));
				String str = "";
				int count = 0;
				while((str = reader1.readLine()) != null) {
					pOut[count] += str + "\n";
					System.out.println(str+"comparelineoutput");
					count++;
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter("execution/compare.txt"));
				writer.write(userOutput);
				writer.flush();
				userOutput = "";
				BufferedReader reader2 = new BufferedReader(new FileReader("execution/compare.txt"));
				String str2 = "";
				while((str2 = reader2.readLine()) != null) {
					for(int index = 0; index < pOut.length; index++) {							
						userOutput += str2 + "\n";
						status[index] = userOutput.equals(pOut[index]);						
					}
				}
				//programOutput = pOut;
				System.out.println(programOutput+"programoutput");
				programModel.Execution(con,request,programName1);
				expectedOutput = viewController.compare(this,programModel,programName);
				response.headers().add("Content-Length", String.valueOf(expectedOutput.length())).add("Content-Type", "text/html");
				response.write(expectedOutput);	
			}
			else {
				viewController.login();
				response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
				response.write(viewController.result);	
			}
		}
	}

	public void getUpdate(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		String result = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("execution/program.txt"));
		int count = 0;
		String part ="";
		while((part = reader.readLine()) != null) {
		count++;
			if(count >= 1) {
				desc +=  part ;
			}
		}
		BufferedReader buffer1 = new BufferedReader(new FileReader("java/" + programName + ".java"));
		String line1 = "";
		while((line1 = buffer1.readLine()) != null) {
			code += line1;
		}
		System.out.println("code==> " + code);
		BufferedReader buffer = new BufferedReader(new FileReader("view/program/add.html"));
		while((line = buffer.readLine()) != null) {
			if(line.contains("Add Program")) {
				line = line.replace("Add Program", "Update Program");	
			}
			if(line.contains("<form action")) {
				result += line.replace("addProgram", "update");
			}
			if(line.contains("id=button")) {
				line = line.replace("Add Program", "Update Program");	
			}
			if(line.contains("Guest")) 
				result += line.replace("Guest", User.username);
			
			else {
				result += line;
			}
		}
		result = result.replace("prt", programName);
		result = result.replace("<input type = \"submit\" value = \"Add\" style = \"width:80px; height:30px;\"/>", "<input type = \"submit\" value = \"Update\" style = \"width:80px; height:30px;\"/>");
		result = result.replace("<h1>tag1</h1>", code);
		result = result.replace("<h1>tag</h1>", desc.split("</pre")[0]);
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
		
	}
	
	public void update(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		
		String result = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("execution/program.txt"));
		int count = 0;
		String part ="";
		while((part = reader.readLine()) != null) {
			count++;
			if(count > 1) {
				desc +=  part ;
			}
		}
		BufferedReader buffer1 = new BufferedReader(new FileReader("java/" + programName + ".java"));
		String line1 = "";
		while((line1 = buffer1.readLine()) != null) {
			code += line1;
		}
		BufferedReader buffer = new BufferedReader(new FileReader("view/program/add.html"));
		while((line = buffer.readLine()) != null) {
			if(line.contains("AddProgram")) 
				line = line.replace("AddProgram", "Update");
			if(line.contains("addProgram")) 
				result += line.replace("addProgram", "update");
			if(line.contains("Guest")) 
				result += line.replace("Guest", User.username);
			else {
				result += line;
			}
		}
		result = result.replace("prt", programName);
		result = result.replace("<input type = \"submit\" value = \"Add\" style = \"width:80px; height:30px;\"/>", "<input type = \"submit\" value = \"Update\" style = \"width:80px; height:30px;\"/>");
		result = result.replace("<h1>tag1</h1>", code);
		result = result.replace("<h1>tag</h1>", desc);		
		srcCode = request.getParam("output");
		desc = request.getParam("input");
		boolean flag = programModel.update(conn, request);
		System.out.println(programName+"update");
		PrintWriter writer = new PrintWriter(new FileWriter("java/" + programName + ".java"));    
		writer.write(srcCode);        
		writer.flush();  
		writer.close();  
		if(flag) {
			programModel.index(conn);
			viewController.index(programModel);
			result += viewController.result;
			result += "<div class=success style=color:green>Program Updated</div>";
		}
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
	}
}
