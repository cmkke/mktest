package org.test.mail;


import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil
{
	private static final String SMTP_SERVER = "smtp.163.com";
	private static final String FROM = "peterjc@163.com";
	private static final String PASSWORD = "peter927";

	private static final Properties properties = new Properties();
	private static final void syncSend(String to, String subject, String content) throws AddressException,
			MessagingException
	{
		Session session = Session.getDefaultInstance(properties);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM));
		message.setSubject(subject);
		message.setText(content);
		message.setSentDate(new Date());
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP_SERVER, FROM, PASSWORD);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		properties.put("mail.host", SMTP_SERVER);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		syncSend("313317945@qq.com", "test", "test2");
	}

}