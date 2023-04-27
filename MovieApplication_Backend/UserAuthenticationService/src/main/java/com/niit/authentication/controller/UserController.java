package com.niit.authentication.controller;

import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.services.SecurityTokenGenerator;
import com.niit.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("user/v1")
public class UserController {

    private UserService userService;

    private SecurityTokenGenerator securityTokenGenerator;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
        this.javaMailSender = javaMailSender;
    }




    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map=null;
        try{
            User user1=userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if(user1.getEmail().equals(user.getEmail())){
                map=securityTokenGenerator.generateToken(user);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setTo(user.getEmail());
                simpleMailMessage.setSubject("Login");
                simpleMailMessage.setText("You have logged in successfully");
                javaMailSender.send(simpleMailMessage);
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            return new ResponseEntity<>("Try after sometimes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user)throws UserAlreadyExistsException {
        User userCreated=userService.addUser(user);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Register");
        simpleMailMessage.setText("You have Register successfully");
        javaMailSender.send(simpleMailMessage);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }



}
