package mappers;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import io.vertx.core.http.*;

import models.*;

public class UserMapper {  
	public static boolean flag = false;
	public static String username;
	
	public static  ResultSet rSet = null;
	
	public boolean login(Connection conn,ArrayList<String> list) throws Exception {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users");
			System.out.println(list.get(0)+"usermapperlogin");
			String user = list.get(0);
			String password = list.get(1);
			
			while(resultSet.next()) {
				//~ System.out.println(resultSet.getString(1));
				//~ System.out.println(user);
				//~ System.out.println(resultSet.getString(3));
				//~ System.out.println(password);
				if(resultSet.getString(1).equals(user) && resultSet.getString(3).equals(password) || user.equals(resultSet.getString(2)) && resultSet.getString(3).equals(password)) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
					Date date = new Date();  
					InetAddress ip=InetAddress.getByName("localhost");  
					String loginDate = formatter.format(date).split(" ")[0]; 
					String time = formatter.format(date).split(" ")[1]; 
					Statement statement1 = conn.createStatement();
					username = user;	
					System.out.println(username+"usernamemapperinwhileloop");
					statement1.executeUpdate("insert into login_histories values(" + "'"+ username +"'" +","+"'" +time + "'"+"," + "'" +loginDate+ "'"+","+ "'"+ ip.getHostAddress() +"'" + ")");  
					flag = true; 	
					System.out.println(flag+"flagusermapperinwhileloop");	
					break;		
				}
				else {
					flag = false;
					System.out.println(flag+"falseflagusermapperinwhileloop");			
				}
			}
			Statement state = conn.createStatement();
			
			if(flag) {
				rSet = state.executeQuery("select *  from users where username = '" + username + "'");
			}
		System.out.println(flag+"flagusermapper");
		return flag;
	}
	
	public boolean signUp(Connection conn,ArrayList<String> list) throws Exception{
			String username = list.get(0);
			String email = list.get(1);
			String password = list.get(2);
			String confirmPassword = list.get(3);
			String address = list.get(4);
			Statement statement = conn.createStatement();
			String mailId = URLDecoder.decode(email,"UTF-8");
			System.out.println(address+"usermappersignupaddress");
			System.out.println(password+"usermappersignuppassword");
			System.out.println(confirmPassword+"usermappersignupconfirmpassword");
			ResultSet rs = statement.executeQuery("select username from users ");
			while(rs.next()) {
				System.out.println(rs.getString(1));
				if(rs.getString(1).equals(username)) {
					flag = false;
					System.out.println(username+"usermappersignup12");
					return flag;
				}
			}
			if( password.equals(confirmPassword)) {
				statement.executeUpdate("insert into users values("+ "'"+ username +"'" +","+"'" + mailId + "'"+"," + "'" +password+ "'"+","+ "'"+address +"'" + ")");
				flag = true; 
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH12:MI:SS AM");  
					Date date = new Date();  
					InetAddress ip=InetAddress.getByName("localhost");  
					String loginDate = formatter.format(date).split(" ")[0]; 
					String time = formatter.format(date).split(" ")[1]; 
					Statement statement1 = conn.createStatement();	
					System.out.println(username);
					statement1.executeUpdate("insert into login_histories values(" + "'"+ username +"'" +","+"'" +time + "'"+"," + "'" +loginDate+ "'"+","+ "'"+ ip.getHostAddress() +"'" + ")");  
			}
			Statement state = conn.createStatement();
			if(flag) {
				rSet = state.executeQuery("select *from users where username = '" + username + "'");
			}
		return flag;
	}

	public boolean executeUpdate(Connection connect,String email,String adress,String name1) throws Exception {						
		Statement statement = connect.createStatement();		
		statement.executeUpdate("update users set username='"+name1+"',email='"+email+"',address='"+adress+"' where username='"+User.username+"'");
		return true;
	}
	
	public ResultSet executeUpdateProfile(Connection connect,String name1) throws Exception {						
		Statement statement = connect.createStatement();		
		ResultSet rs = statement.executeQuery("select * from users where username ='"+name1+"'");
		return rs;
	}
}
