package controllers;
import models.*;
import mailer.*;
import io.vertx.core.http.*;
import java.sql.*;
import java.net.URLDecoder;
import java.io.*;
import java.util.*;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;  
import javax.mail.Authenticator;
import javax.activation.*;
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

public class MailersController {
	static String result = "";
	static String fileName = "";
	ViewsController  viewController = new ViewsController();
	LoginHistory login = new LoginHistory();
	Mailer mail = new Mailer();	
	ProgramHistory program = new ProgramHistory();
	
	public void send(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		fileName = request.getParam("file");
		System.out.println("...." + fileName);
		viewController.send(request);		
		boolean flag = mail.sendMail(fileName,request);	
		//generatePdf(conn,request,response);
		if(flag) {
			viewController.send(request);
			viewController.result += "<div class=success style=color:green>mail sent</div>";
		}	
		response.headers().add("Content-Length", String.valueOf(viewController.result.length())).add("Content-Type", "text/html");
		response.write(viewController.result);
	}
	
	public void generatePdf(Connection conn, HttpServerRequest request, HttpServerResponse response) throws Exception {
		String result = "";
			if(request.uri().contains("history")) {
				login.executeQuery(conn);
				result = new ViewsController().loginHistory(login);
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf/Login.pdf"));
				document.open();
				HTMLWorker htmlWorker = new HTMLWorker(document);
				String pdfresult ="";
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				InetAddress ip = InetAddress.getByName("localhost");
				String loginDate = formatter.format(date).split(" ")[0]; 
				String time = formatter.format(date).split(" ")[1];
				pdfresult += "<h3 style=text-align:left>Downloaded By:"+User.username+"</h3>";
				pdfresult += "<h3 style=text-align:left> Date"+loginDate+"</h3>";
				pdfresult += "<h3 style=text-align:left>Time Date"+time+"</h3>";
				pdfresult += login.result;
				htmlWorker.parse(new StringReader(pdfresult));
				document.close();
				result += "<div class=success style=color:green>Pdf Generated</div>";
				response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
				response.write(result);	
			}
			else {
				program.displayProgramHistory(conn);
				result = new ViewsController().programHistory(program);
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf/Program.pdf"));
				document.open();
				HTMLWorker htmlWorker = new HTMLWorker(document);
				System.out.println(program.result);
				String pdfresult ="";
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				InetAddress ip = InetAddress.getByName("localhost");
				String loginDate = formatter.format(date).split(" ")[0]; 
				String time = formatter.format(date).split(" ")[1];
				pdfresult += "<h3 style=text-align:left>Downloaded By:"+User.username+"</h3>";
				pdfresult += "<h3 style=text-align:left> Date"+loginDate+"</h3>";
				pdfresult += "<h3 style=text-align:left>Time Date"+time+"</h3>";
				pdfresult += program.result;
				htmlWorker.parse(new StringReader(pdfresult));
				document.close();
				result += "<div class=success style=color:green>Pdf Generated</div>";
				response.headers().add("Content-Length", String.valueOf(result.length())).add("Content-Type", "text/html");
				response.write(result);	
			}
	}
}
