package models;
import java.io.*;
import java.sql.*;
import java.util.*;
import io.vertx.core.http.*;

import mappers.*;
public class User {
	UserMapper userMapper = new UserMapper();
	public static boolean flag = false;
	public static String username = "";
	public static String uName = "";
	public static String email = "";
	public static String address = ""; 
	
	
	public boolean login(Connection conn,ArrayList<String> list) throws Exception {
		
		flag = userMapper.login(conn,list);
		if(UserMapper.flag) {
			flag = true;
			username = list.get(0);
			System.out.println(username+"usernameusermodel");
			ResultSet set = UserMapper.rSet;
			if(set.next()) {
				username = set.getString(1);
				email = set.getString(2);
				address = set.getString(4);
			}
			username = UserMapper.username;
		}
		System.out.println("LOGIN USER MODELuname ==> " + username + "email==>" + email + "address==> " + address);
		return flag;
	}
	
	public boolean signUp(Connection conn,ArrayList<String> list) throws Exception {	
		flag = userMapper.signUp(conn,list);
			if(flag) {
				username = list.get(0);
				ResultSet set = UserMapper.rSet;
				if(set.next()) {
					username = set.getString(1);
					email = set.getString(2);
					address = set.getString(4);
				}
			}
		System.out.println("uname ==> " + User.username + "email==>" + User.email + "address==> " + User.address);
		return flag;
	}
	
	public boolean updateProfile(Connection connect,String email,String adress,String name1) throws Exception {						
		boolean flag = userMapper.executeUpdate(connect,email,adress,name1);
		if(flag) {
			executeProfile(connect,name1);
			username = name1;
		}
		return flag;
	}
	
	public void executeProfile(Connection connect,String name1) throws Exception {							
		ResultSet rs = userMapper.executeUpdateProfile(connect,name1);
		while(rs.next()) {	
			username = rs.getString(1);
			email = rs.getString(2);
			address = rs.getString(4);	
			System.out.println(address+"address user");
		}
	}
}
