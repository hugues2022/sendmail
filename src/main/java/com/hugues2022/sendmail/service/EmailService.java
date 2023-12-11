package com.hugues2022.sendmail.service;

import java.util.Date;
import java.util.List;

import com.hugues2022.sendmail.entity.EmailC;

public interface EmailService {

    EmailC create(EmailC email);

    void delete(EmailC email);

    List<EmailC> getListeEmail(Date debut, Date fin, int pageNumber, int pageSize);

    List<EmailC> getListeEmailNotSent(int pageNumber, int pageSize);

    List<EmailC> findAll();

    List<EmailC> findAll(int pageNumber, int pageSize);
    
}
