package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.mail.internet.MimeBodyPart;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailContent {

    private String senderAddress;

    private String senderPassword;

    private String recipientAddress;

    private List<String> ccAddresses;

    private String subject;

    private MimeBodyPart textBodyPart;
    private MimeBodyPart attachmentBodyPart;
}
