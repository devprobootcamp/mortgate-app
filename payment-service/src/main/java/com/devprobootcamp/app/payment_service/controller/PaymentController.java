package com.devprobootcamp.app.payment_service.controller;

import com.devprobootcamp.app.payment_service.dto.PaymentRequest;
import com.devprobootcamp.app.payment_service.dto.PaymentResponse;
import com.devprobootcamp.app.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:58 AM<br/>
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<PaymentResponse> makePaymentTransaction(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.makePayment(paymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<PaymentResponse> getHomePaymentBalance(@PathVariable("homeId") String homeId) {
        PaymentResponse paymentResponse = paymentService.getPaymentBalance(homeId);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
}
