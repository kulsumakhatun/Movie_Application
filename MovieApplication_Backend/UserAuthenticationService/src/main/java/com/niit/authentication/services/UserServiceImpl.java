package com.niit.authentication.services;

import com.niit.authentication.domain.Email;
import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmail()).isPresent()){
            throw  new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email,password);
        System.out.println(email + password);
        if(user == null){
            throw  new UserNotFoundException();
        }
        return user;
    }

    public void sendEmail(Email email){
        SimpleMailMessage msg=new SimpleMailMessage();
        try{
            if(email.getPassword()==null){
                msg.setTo(email.getEmailId());
                msg.setSubject("Registration");
                msg.setText("You have successfully registered.Please continue with login.");
                javaMailSender.send(msg);

            }
            else {
                msg.setTo(email.getEmailId());
                msg.setSubject("Password");
                msg.setText(" Your Password is " + email.getPassword());
                javaMailSender.send(msg);
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
}
