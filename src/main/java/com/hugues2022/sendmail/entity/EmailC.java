package com.hugues2022.sendmail.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_email")
@Getter
@Setter
public class EmailC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origine;
    private String destination;
    private String sujet;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_envoi")
    private Date dateEnvoi;
    @Enumerated(EnumType.STRING)
    private Status status;


}