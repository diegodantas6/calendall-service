package br.com.calendall.utils;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Stateless
public class Email {

	public boolean enviaEmail(String emailTo, String nomeTo, String assunto, String corpo) {
		try {
			String nomeFrom = "Copa";
			String emailFrom = "copasdoscampeoes@gmail.com";
			String senha = "copa2016";
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			//props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.port", "465");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFrom, senha);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom, nomeFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo, nomeTo));
			
	        message.setHeader("Content-Type", "text/html; charset=ISO-8859-1");
	        message.setHeader("X-Accept-Language", "pt-br, pt");
	        message.setHeader("Content-Transfer-Encoding", "8bit");	
			
			message.setSubject(assunto, "iso-8859-1");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(corpo, "text/html; charset=ISO-8859-1");
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
	        
			message.setContent(multipart);
			
			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
