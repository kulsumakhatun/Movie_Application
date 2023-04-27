package com.niit.authentication.domain;

public class Email {

    private String emailId;
    private String subject;
    private String body;
    private String password;

    public Email(String emailId, String subject, String body, String password) {
        this.emailId = emailId;
        this.subject = subject;
        this.body = body;
        this.password = password;
    }

    public Email() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailId='" + emailId + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
