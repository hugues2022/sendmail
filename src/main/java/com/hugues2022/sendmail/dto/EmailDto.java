package com.hugues2022.sendmail.dto;

import javax.validation.constraints.NotBlank;

import com.hugues2022.sendmail.entity.EmailC;




public class EmailDto {
    
    private Long id;
    private String origine;
    @NotBlank(message = "{validation.field.destination.blank}")
    private String destination;
    @NotBlank(message = "{validation.field.sujet.blank}")
    private String sujet;
    private String content;


    public EmailDto(Long id, String origine, String destination, String sujet, String content) {
        this.id = id;
        this.origine = origine;
        this.destination = destination;
        this.sujet = sujet;
        this.content = content;
    }


    public EmailDto(EmailC email) {
        this.id = email.getId();
        this.origine = email.getOrigine();
        this.destination = email.getDestination();
        this.sujet = email.getSujet();
        this.content = email.getContent();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigine() {
        return this.origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSujet() {
        return this.sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailC convertToEmail(){
        EmailC o= new EmailC();
        o.setId(this.getId());
        o.setOrigine(this.getOrigine());
        o.setDestination(this.getDestination());
        o.setSujet(this.getSujet());
        o.setContent(this.getContent());
        return o;
    }

}
