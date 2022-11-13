package org.stepaniuk.taxipark.Email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.stepaniuk.taxipark.Email.service.EmailService;

import javax.mail.MessagingException;

@SpringBootApplication
public class Email {
    private final EmailService emailService;

    public Email(EmailService emailService) {
        this.emailService = emailService;
    }

    public void run() {
        SpringApplication.run(Email.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() throws MessagingException {
        emailService.sendEmailWithAttachment("The program ended with an error or exception!","ckujlobou.boban@gmail.com",
                "Program logs",
                "D:\\Java\\JavaProgr\\Laboratory-Work_4-8\\Taxi-Park\\logs\\taxiLogs\\logs.log");
    }
}

