package com.teentech.hotels.service;

import com.teentech.hotels.dto.EmailDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
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
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(System.getenv("EMAIL_ADDRESS"), System.getenv("EMAIL_PASSWORD"));
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getTo()));

            if (emailDto.getCc() != null) {
                for (String ccMail : emailDto.getCc()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMail));
                }
            }

            message.setSubject(emailDto.getSubject());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailDto.getContent(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            ByteArrayOutputStream baos = emailDto.getAttachmentFile();
            if (baos != null) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/octet-stream");
                attachmentBodyPart.setDataHandler(new DataHandler(attachment));
                attachmentBodyPart.setHeader("Content-Disposition", "attachment; filename=\"ReservationOutput.docx\"");
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);
            //send message
            Transport.send(message);

            log.info("message sent successfully");
        } catch (Exception e) {
            log.error("Error while sending the email:", e);
            throw new MessagingException(e.getMessage());
        }
    }
}