package com.hugues2022.sendmail.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hugues2022.sendmail.entity.EmailC;
import com.hugues2022.sendmail.entity.Status;

public interface EmailRepository extends JpaRepository<EmailC, Long>{
    
    @Query("SELECT e FROM EmailC e WHERE e.dateEnvoi BETWEEN ?1 AND ?2")
    List<EmailC> getListeEmailByDate(Date debut, Date fin, Pageable page);

    @Query("SELECT e FROM EmailC e WHERE e.status = :status")
    List<EmailC> getListeEmailNotSent(@Param("status") Status status, Pageable page);

}
