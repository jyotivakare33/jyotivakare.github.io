package controllers;
import java.io.*;
import java.util.*;
import java.sql.*;
import io.vertx.core.http.*;

import lib.HttpParser;
public class AssetsController {
	HttpParser parser = new HttpParser();
	public void readCss(Connection conn,HttpServerRequest request, HttpServerResponse response) throws Exception{
		String list = parser.splitURL(request).get(2).split("=")[1];
		response.putHeader("Content-Type","text/css");
		String line = "";
		String result = "";
			BufferedReader buffer = new BufferedReader(new FileReader("assets/css/" + list));
			while((line = buffer.readLine()) != null ){
				result += line;
			}				
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
	}
	
	public void image(Connection conn,HttpServerRequest request, HttpServerResponse response) throws Exception {
		response.putHeader("Content-Type","image/png");
		String list = parser.splitURL(request).get(2).split("=")[1];
		response.sendFile("assets/images/" + list);
	}
	
	public void readJs(Connection conn,HttpServerRequest request, HttpServerResponse response) throws Exception{
		String list = parser.splitURL(request).get(2).split("=")[1];
		response.putHeader("Content-Type","text/javascript");
		String line = "";
		String result = "";
			BufferedReader buffer = new BufferedReader(new FileReader("assets/javascript/" + list));
			while((line = buffer.readLine()) != null ){
				result += line;
			}	
			System.out.println(list+"javascriptfile");				
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
	}
}
