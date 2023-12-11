package com.hugues2022.sendmail.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hugues2022.sendmail.dao.EmailRepository;
import com.hugues2022.sendmail.entity.EmailC;
import com.hugues2022.sendmail.entity.Status;
import com.hugues2022.sendmail.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService{

    private EmailRepository emailRepository;
    @Override
    public EmailC create(EmailC email) {
        return emailRepository.save(email);
    }

    @Override
    public void delete(EmailC email) {
        emailRepository.delete(email);
    }

    @Override
    public List<EmailC> getListeEmail(Date debut, Date fin, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return emailRepository.getListeEmailByDate(debut, fin, pageable);
    }

    @Override
    public List<EmailC> getListeEmailNotSent(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return emailRepository.getListeEmailNotSent(Status.UNSENT, page);
    }

    @Override
    public List<EmailC> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public List<EmailC> findAll(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return emailRepository.findAll(page).getContent();
    }

}  
