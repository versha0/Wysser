package com.stackroute.admin.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "contactustable")
public class ContactUs {
    @Id
    String email;
    String name;
    String subject;
    String message;
    Boolean replyStatus;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Boolean replyStaus) {
        this.replyStatus = replyStaus;
    }

    public ContactUs() {
    }

    public ContactUs(String email, String name, String subject, String message, Boolean replyStatus) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.replyStatus = replyStatus;
    }
}
