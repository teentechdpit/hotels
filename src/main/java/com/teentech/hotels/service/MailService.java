package com.teentech.hotels.service;

import com.teentech.hotels.dto.EmailDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;
import java.util.Properties;

@Service
@Log4j2
public class MailService {
    public void send(EmailDto emailDto) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.checkserveridentity", true);
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(System.getenv("EMAIL_ADRESS"), System.getenv("EMAIL_PASSWORD"));
                    }
                });
        //compose message


        //compose message
        try {
            MimeMessage message = new MimeMessage(session);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getTo()));

            for (String ccMail : emailDto.getCc()) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMail));
            }

            message.setSubject(emailDto.getSubject());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailDto.getContent(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(new File("src\\main\\resources\\templates\\ReservationOutput.docx"));

                multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);
            //send message
            Transport.send(message);

            log.info("message sent successfully");
        } catch (Exception e){
            log.error("Error while sending the email:", e);
            throw new MessagingException(e.getMessage());
        }
    }
}