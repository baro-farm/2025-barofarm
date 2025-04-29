package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailTest {
 public static void main(String[] args) {
     Properties props = new Properties();
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");

     Session session = Session.getInstance(props, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("jihyeon1749@gmail.com", "ound hcnr mwpb aclt");
         }
     });

     try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("jihyeon1749@gmail.com"));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jihyeon1745@naver.com"));
         message.setSubject("테스트 메일");
         message.setText("정상 동작 확인");
         Transport.send(message);
         System.out.println("메일 전송 성공");
     } catch (MessagingException e) {
         e.printStackTrace();
     }
 }
}
