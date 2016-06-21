package br.com.rp.services;

import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailService {
	
	@Asynchronous
	public void enviarEmail(String destinatario, String assunto, String conteudo) throws MessagingException{

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("java.posbank@gmail.com", "pos.java");
			}
		});

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("java.posbank@gmail.com"));
		mimeMessage.setText(conteudo);
		mimeMessage.setSubject(assunto);
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

		Transport.send(mimeMessage);
	}
}
