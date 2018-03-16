package mailer;
import models.*;
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

public class Mailer {
	static String result = "";
	
	public boolean sendMail(String filename, HttpServerRequest request) throws Exception {
		String Email_Id = "jyotivakare34@gmail.com";
	    String password = "opara@1234";
	    String mail_subject = "PDF File";
	    boolean flag = false;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465"); 
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.port", "465");  
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication("jyotivakare34@gmail.com","opara@1234");  
			}  
		});        
		if(request.getParam("email") != null) {
			System.out.println(request.getParam("email"));
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Email_Id));
			message.setSubject(mail_subject);
			MimeMultipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			InternetAddress[] recipient_mail_id =  getInternetAddresses(request.getParam("email"));
			message.addRecipients(Message.RecipientType.TO,recipient_mail_id);
			InternetAddress[] CcAddress =  getInternetAddresses(request.getParam("emails")); 
			message.setRecipients(javax.mail.Message.RecipientType.CC, CcAddress); 
			InternetAddress[] BccAddress = getInternetAddresses(request.getParam("Cc"));
			message.setRecipients(javax.mail.Message.RecipientType.BCC, BccAddress);
			DataSource source = new FileDataSource("pdf/"+filename);  
			messageBodyPart.setDataHandler(new DataHandler(source));    
			messageBodyPart.setFileName("pdf/"+filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");     
			transport.connect("smtp.gmail.com", Email_Id, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent Email successfully....");	
			flag = true;	
		}
		return flag;
    }
    
    private static InternetAddress[] getInternetAddresses(String recipients) throws AddressException {
		ArrayList<String> recipientsArray = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(recipients, ",");
		while (st.hasMoreTokens()) 
		{
			recipientsArray.add(st.nextToken());
		}
		int sizeTo = recipientsArray.size();
		InternetAddress[] ainternetaddress1 = new InternetAddress[sizeTo];
		for (int i = 0; i < sizeTo; i++) 
		{
			ainternetaddress1[i] = new InternetAddress(recipientsArray.get(i).toString());
		}
		return ainternetaddress1;
	}
}
