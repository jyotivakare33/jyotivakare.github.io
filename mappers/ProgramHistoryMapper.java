package mappers;
import models.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.*;
import java.time.*;
import java.util.Calendar;

import controllers.*;
public class ProgramHistoryMapper {
	public String result = "";
	
	public void executeQuery(Connection conn) throws Exception{
		System.out.println("reached programhistorymapper");
		String input = "";
		String output = "";
		String line1 = "";
		String line2 = "";
		int count = 1;
		Statement statement1 = conn.createStatement();
		Statement sql = conn.createStatement();			
		BufferedReader reader = new BufferedReader(new FileReader("execution/input.txt"));
		BufferedReader reader1 = new BufferedReader(new FileReader("execution/output.txt"));
		while((line1 = reader.readLine()) != null) {
			input += line1 + "\n";
		}
		while((line2 = reader1.readLine()) != null) {
				String str = line2.replaceAll("\\\\n" , "\n");	
				output += str ; 
		}
		System.out.println("reached programhistorymapper2");
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		InetAddress ip = InetAddress.getByName("localhost");
		String loginDate = formatter.format(date).split(" ")[0]; 
		String time = formatter.format(date).split(" ")[1];
		System.out.println("reached programhistorymapper2");
		statement1.executeUpdate("insert into program_histories values(default,'"+ User.username + "','"+ ProgramsController.programName + "','" + input + "','" +  output + "','" + loginDate + "','" + time + "','" + ip.getHostAddress() + "')");
		System.out.println("inserted successfully");
	}
	
	public void displayProgramHistory(Connection conn) throws Exception {
		int count = 1;
		System.out.println(ProgramsController.programName+"programhistorymapper");
		Statement statement = conn.createStatement();
		
		ResultSet resultSet =  statement.executeQuery("select *from program_histories where program_name ='" + ProgramsController.programName + "' and username='"+User.username+"' order by date desc,time desc;");
		result += "<h1 style=text-align:center>"+ProgramsController.programName+"</h1>";
		
		result = result +"<table align = center width = 60% height = 10% border = 2 id=myTable><tr><th>SL_NO</th><th>Program Name</th><th>Input</th><th>Output</th><th>Date</th><th>Time</th><th>IP Address</th></tr>";
		while(resultSet.next()) {
			System.out.println("inserted successfully");
			result += "<tr><td style = text-align:center;>"+count+"</td><td style = text-align:center;>" + resultSet.getString(3) +"</td><td style = text-align:center;><textarea id=history readonly>"+ resultSet.getString(4);
			result +=  "</textarea></td><td style = text-align:center;><textarea id=history readonly>"+ resultSet.getString(5) +"</textarea></td><td style = text-align:center;>"+ resultSet.getString(6) + "<td style = text-align:center;>"+ resultSet.getString(7) + "</td><td style = text-align:center;>"+ resultSet.getString(8) + "</td></tr>";
			count++;
		}
		result += "</table>";
	}
}
