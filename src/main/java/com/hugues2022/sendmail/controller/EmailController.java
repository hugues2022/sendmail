package com.hugues2022.sendmail.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugues2022.sendmail.dto.EmailDto;
import com.hugues2022.sendmail.entity.EmailC;
import com.hugues2022.sendmail.entity.Status;
import com.hugues2022.sendmail.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/email")
public class EmailController {
    
    
    private final JavaMailSender sender;
    private final EmailService emailService;
    
    @Value("${spring.mail.username}")
    private String from;


    public EmailController(JavaMailSender jms, EmailService service){
        this.sender = jms;
        this.emailService = service;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@Validated @RequestBody EmailDto emailDto){
        emailDto.setOrigine(from);
        EmailC email = emailDto.convertToEmail();
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailDto.getOrigine());
            helper.setTo(emailDto.getDestination());
            helper.setSubject(emailDto.getSujet());
            helper.setText(emailDto.getContent());
            sender.send(message);
            email.setStatus(Status.SENT);
        } catch (Exception e) {
            email.setStatus(Status.UNSENT);
        } finally{
            emailService.create(email);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getEmails")
    public List<EmailDto> getListeEmail() {
        List<EmailC> emails = emailService.findAll();
        return emails.stream().map(EmailDto :: new).collect(Collectors.toList());
    }
    
    @GetMapping("/getEmails")
    public ResponseEntity<?> getListeEmailByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EmailC> emails = emailService.findAll(page, size);        
        return new ResponseEntity<>(emails.stream().map(EmailDto :: new).collect(Collectors.toList()), HttpStatus.OK);
    }

}
