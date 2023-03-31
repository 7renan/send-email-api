package msemail.sendemail.controllers;

import jakarta.validation.Valid;
import msemail.sendemail.dtos.EmailDto;
import msemail.sendemail.models.EmailModel;
import msemail.sendemail.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return  new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public Map<String, String> welcome(){
        return emailService.welcome();
    }
}
