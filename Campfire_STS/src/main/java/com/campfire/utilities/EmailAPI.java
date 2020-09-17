package com.campfire.utilities;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailAPI {
    
    public static int sendEmail(String userFirstname, String reciptientEmail, String newPassword) {
        
    	//this is the email address that will send end-user emails
        final String username = "campfire.revature@gmail.com";
        final String password = "revatureProject";
    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.post", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("CampFire"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciptientEmail));
            message.setSubject("Your new Password");
            message.setText("Dear " + userFirstname +",\nWe are very sorry you lost your password, here is a temp password you can use: \n\n" + newPassword
                    + "\n\n Thank You,\nCamfire Team");
            
            Transport.send(message);
            System.out.println("done");
            return 1;
        } catch(MessagingException e) {
            e.printStackTrace();
            return -1;
            
        }
    }
}