package com.devprobootcamp.app.notification_service.controller;

import com.devprobootcamp.app.notification_service.dto.EmailContentDTO;
import com.devprobootcamp.app.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:29 AM<br/>
 */
@RestController
@RequestMapping("/notifications")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<Void> createHome(@RequestBody EmailContentDTO emailContentDTO) {
        emailService.sentEmail(emailContentDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
