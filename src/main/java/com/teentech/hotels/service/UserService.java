package com.teentech.hotels.service;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRepository;
import com.teentech.hotels.util.UserConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto getUserByName(String userName, String password) {
        Optional<User> user = userRepository.findById(userName);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (user.isPresent() && bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            UserDto userDto = UserConverter.convertFromEntityToDto(user.get());
            return userDto;
        }
        return null;
    }

    public User getUserByName(String userName) {
        Optional<User> user = userRepository.findById(userName);
        return user.orElse(null);
    }

    public User getAuthenticatedUser(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName, password);
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void sendEmailForAuth(String to, String uuid) throws MessagingException {
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
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(System.getenv("EMAIL_ADRESS"), System.getenv("EMAIL_PASSWORD"));
                    }
                });
        //compose message

        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Confirm authentication to HotelListe");
        String applicationHost = System.getenv("APPLICATION_HOST");
        String mailText = "Link for confirm your mail and set the password " + applicationHost + "/users/confirmation/" + uuid;
        message.setText(mailText);
        Transport.send(message);
        log.info("Email send successfully to address {}", to);

    }
}
