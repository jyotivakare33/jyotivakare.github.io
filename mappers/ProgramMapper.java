package mappers;
import java.sql.*;
import java.io.*;
import io.vertx.core.http.HttpServerRequest;

import controllers.*;
public class ProgramMapper {
	public String result = "";
	static String description = "";
	public static String descriptionName ="";
	public void index(Connection conn) throws Exception {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select *from programs order by sl_no");
			while(resultSet.next()) {
				result += "<tr><td >"+resultSet.getString(1) +"</td><td >"+resultSet.getString(2)+"</td><td>" + "<a href = "+ "/ProgramsController" +"/show" + "?file=" +resultSet.getInt(1)+ "><div class=button> Go To Description </div></a>" + "</td></tr>";	
			}
	}
	
	public void show(Connection conn, HttpServerRequest request)  throws Exception{
		String param = request.getParam("file");
		System.out.println("param ==>" + param);
			Statement statement = conn.createStatement();
			ResultSet resultSet =  statement.executeQuery("select action from programs where sl_no ="+ param);
			while(resultSet.next()) {
				result +=  resultSet.getString(1) ;
			}
			Statement statement1 = conn.createStatement();
			ResultSet resultSet1 =  statement1.executeQuery("select program_name from programs where sl_no ="+ param);
			while(resultSet1.next()) {
				descriptionName = resultSet1.getString(1);
				ProgramsController.programName =  resultSet1.getString(1).replaceAll("\\s","") ;
			}
	}
	
	public void Execution(Connection conn, HttpServerRequest request,String programName) throws Exception {
			System.out.println(programName+"programnamemapper");
			Statement statement = conn.createStatement();
			ResultSet resultSet =  statement.executeQuery("select action from programs where sl_no ="+ programName);
			while(resultSet.next()) {
				result +=  resultSet.getString(1) ;
			}
			Statement statement1 = conn.createStatement();
			ResultSet resultSet1 =  statement1.executeQuery("select program_name from programs where sl_no ="+ programName);
			while(resultSet1.next()) {
				descriptionName = resultSet1.getString(1);
				ProgramsController.programName =  resultSet1.getString(1).replaceAll("\\s","") ;
			}
	}
	
	public boolean add(Connection conn, HttpServerRequest request) throws Exception {
		String description = request.getParam("input");
		boolean flag = false;
		String pname = request.getParam("pname");
		this.description = pname + "\n" + description;
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into programs values(DEFAULT,"+ "'"  + pname + "'"  + "," + "'" + description + "')" );
			System.out.println("successfully inserted");
			flag = true;
		return flag;
	}
	
	public boolean update(Connection conn,  HttpServerRequest request) throws Exception {
		boolean flag = false;
			Statement statement = conn.createStatement();
			String desc = request.getParam("input");
			String name = request.getParam("pname");
			String output = request.getParam("output");
			desc =  ProgramsController.programName + "\n" +  desc ;
			System.out.println("desc====>" + desc);
			statement.executeUpdate("update programs set action = '"+ desc +"', program_name='"+name+"' where sl_no = " + ProgramsController.programName1);
			System.out.println("update programs set action = '"+ desc +"' , program_name='"+name+"' where sl_no = " + ProgramsController.programName1);
			flag = true;
			ProgramsController.programName = name;
			System.out.println("successfully update");
			description = desc;
		return flag;
	}
}
