package models;
import java.io.*;
import java.sql.*;
import java.util.*;
import io.vertx.core.http.*;

import mappers.*;
public class Program {
	ProgramMapper programMapper = new ProgramMapper();
	public String result = "";
	
	public void index(Connection conn) throws Exception {
			programMapper.index(conn);
			result = programMapper.result;
	}
	
	public void show(Connection conn,HttpServerRequest request) throws Exception {
		programMapper.show(conn,request);
		result = programMapper.result;
	}
	
	public void Execution(Connection conn,HttpServerRequest request,String programName) throws Exception {
		programMapper.Execution(conn,request,programName);
		result = programMapper.result;
	}
	
	public boolean add(Connection conn, HttpServerRequest request) throws Exception {	
		return programMapper.add(conn,request);
	}
	
	public boolean update(Connection conn,  HttpServerRequest request) throws Exception {
		return programMapper.update(conn, request);
	}
}
