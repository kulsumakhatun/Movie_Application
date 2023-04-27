package org.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification/")
public class NotificationController {

   private JavaMailSender javaMailSender ;

   @Autowired
    public NotificationController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("message/{email}")
    public void notificationSender(@RequestBody String message, @PathVariable String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Login");
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}
