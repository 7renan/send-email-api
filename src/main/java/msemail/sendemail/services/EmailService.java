package msemail.sendemail.services;
import java.util.HashMap;
import java.util.Map;

import jakarta.transaction.Transactional;
import msemail.sendemail.enums.StatusEmail;
import msemail.sendemail.models.EmailModel;
import msemail.sendemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Dictionary;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public Map<String, String> welcome(){
        Map<String, String> message = new HashMap<>();
        message.put("Message", "Welcome to API send email using java/spring");
        return message;
    }
}
