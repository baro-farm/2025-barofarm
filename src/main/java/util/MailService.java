package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailService {
 public boolean sendMail(Mail mail) {
     final String username = "jihyeon1749@gmail.com";
     final String password = "ound hcnr mwpb aclt";

     Properties props = new Properties();
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");

     Session session = Session.getInstance(props, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
        	 System.out.println("발송자 : "+username+password);
             return new PasswordAuthentication(username, password);
         }
     });

     try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
         message.setSubject(mail.getSubject());
         message.setText(mail.getContent());

         Transport.send(message);
         System.out.println("MailService/메일 전송 성공");
         return true;
     } catch (MessagingException e) {
         e.printStackTrace();
         System.out.println("MailService/메일 전송 실패");
         return false;
     }
 }
}
