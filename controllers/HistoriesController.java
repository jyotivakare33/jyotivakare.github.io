package controllers;
import io.vertx.core.http.*;
import java.sql.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.*;
import java.time.*;
import java.util.Calendar;


import models.*;
import mappers.*;

public class HistoriesController {
    ViewsController  viewController = new ViewsController();
	ProgramHistory program = new ProgramHistory();
	LoginHistory login = new LoginHistory();
	
	public void loginHistory(Connection conn,HttpServerRequest request, HttpServerResponse response) throws Exception {
		String result = "";
		login.executeQuery(conn);
		result = viewController.loginHistory(login);
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
	}
	
	public void programHistory(Connection conn,HttpServerRequest request, HttpServerResponse response) throws Exception {
		String result = "";
		program.displayProgramHistory(conn);
		result = viewController.programHistory(program);
		response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
		response.write(result);	
	}
}
