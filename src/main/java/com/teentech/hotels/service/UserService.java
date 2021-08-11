package com.teentech.hotels.service;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRepository;
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

///necesar @sevice
@Service
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
            UserDto userDto = new UserDto();
            userDto.setMail(user.get().getMail());
            userDto.setUsername(user.get().getUsername());
            userDto.setRoleName(user.get().getRoles().getName());
            userDto.setLanguage(user.get().getLanguage());
            List<String> rights = new ArrayList<>();
            user.get().getRoles().getRights().stream().forEach(r -> rights.add(r.getName()));
            userDto.setRights(rights);
            return  userDto;
        }
        return null;
    }

    public User getUserByName(String userName) {
        Optional<User> user = userRepository.findById(userName);
        return user.get();
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

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void sendEmailForAuth(String from,String password,String to){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
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
            message.setSubject("CONFIRM_AUTHENTIFICATION");
            message.setText("Link for finalization: localhost:8080/users/confirmation");
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
