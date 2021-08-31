package com.teentech.hotels.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
@Log4j2
public class MailService {
    public static void send(String from, String password, String to, List<String> cc, String subject, Multipart multipart) throws Exception {

        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);

            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            for (String ccMail: cc) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMail));
            }

            message.setSubject(subject);

            message.setContent(multipart);
            //send message
            Transport.send(message);

            log.info("message sent successfully");
        } catch (MessagingException e) {
            throw new MessagingException("Error at sending the mail", e);
        }
    }
}