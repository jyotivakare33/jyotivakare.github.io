package mappers;
import models.*;
import java.sql.*;
import java.io.*;
import models.*;
public class LoginHistoryMapper {
	public 	String result = "";
	public void executeLoginQuery(Connection conn) throws Exception {
		String line = "";
		int count = 1;
			Statement statement = conn.createStatement();
			System.out.println(User.username+"usernameloginhistory");
			ResultSet resultSet =  statement.executeQuery("select *from login_histories where username ='"+User.username+"' order by date desc,time desc;");
			result += "<h1 align=center><b>"+User.username+"</b></h1>";
			result = result + "<table align=center width=60% height=10% border=1 id=myTable><tr><th>Slno</th><th>Time</th><th>Date</th><th>IP Address</th></tr>";
			while(resultSet.next()) {
				result += "<tr><td style=text-align:center;>"+count+"<td style=text-align:center;>"+resultSet.getString(2)+"</td><td style=text-align:center;>" +resultSet.getString(3)+"</td><td style = text-align:center;>"+resultSet.getString(4)+"</td></tr>";
				count++;
			}
			result += "</table>";
	}
}
