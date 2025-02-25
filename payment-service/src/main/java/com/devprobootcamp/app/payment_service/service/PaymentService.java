package com.devprobootcamp.app.payment_service.service;

import com.devprobootcamp.app.payment_service.dto.HomeResponse;
import com.devprobootcamp.app.payment_service.dto.PaymentRequest;
import com.devprobootcamp.app.payment_service.dto.PaymentResponse;
import com.devprobootcamp.app.payment_service.entity.PaymentTransactionEntity;
import com.devprobootcamp.app.payment_service.exception.AppValidationException;
import com.devprobootcamp.app.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:56 AM<br/>
 */
@Service
public class PaymentService {


    @Value("${app.service.mort-service}")
    private String mortServiceUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository paymentRepository;


    public PaymentResponse makePayment(PaymentRequest paymentRequest) {


        HomeResponse homeResponse = callMortgageService(paymentRequest.getHomeId());

        if (paymentRequest.getAmount() < homeResponse.getMortgageAmount()) {
            throw new AppValidationException("Amount must not be less than mortgage amount");
        }
        Double additionalAmt = paymentRequest.getAmount() - homeResponse.getMortgageAmount();

        Double interestAmt = (homeResponse.getInterestRate() / 100) * homeResponse.getMortgageAmount();

        double balance = homeResponse.getTotalPrice() - (homeResponse.getMortgageAmount() - interestAmt) - additionalAmt;
        Optional<PaymentTransactionEntity> paymentTransactionEntityOptional = paymentRepository.findLatestPayment(paymentRequest.getHomeId());
        if (paymentTransactionEntityOptional.isPresent()) {
            PaymentTransactionEntity paymentTransactionEntity = paymentTransactionEntityOptional.get();

            balance = paymentTransactionEntity.getBalance() - (homeResponse.getMortgageAmount() - interestAmt) - additionalAmt;
        }

        PaymentTransactionEntity paymentTransactionEntity = new PaymentTransactionEntity();
        paymentTransactionEntity.setAmount(homeResponse.getMortgageAmount());
        paymentTransactionEntity.setHomeId(paymentRequest.getHomeId());
        paymentTransactionEntity.setAdditionalAmount(additionalAmt);
        paymentTransactionEntity.setPaymentDate(LocalDateTime.now());
        paymentTransactionEntity.setInterestAmount(interestAmt);
        paymentTransactionEntity.setBalance(balance);
        paymentRepository.save(paymentTransactionEntity);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentDate(paymentTransactionEntity.getPaymentDate());
        response.setAmount(paymentTransactionEntity.getAmount());
        response.setHomeId(paymentTransactionEntity.getHomeId());
        response.setInterestAmount(paymentTransactionEntity.getInterestAmount());
        response.setBalance(paymentTransactionEntity.getBalance());
        response.setAdditionalAmount(paymentTransactionEntity.getAdditionalAmount());

        return response;
    }

    public List<PaymentResponse> getPaymentBalance(String homeId) {
        return null;
    }


    private HomeResponse callMortgageService(String homeId) {
        ResponseEntity<HomeResponse> account = restTemplate.getForEntity(mortServiceUrl + "/" + homeId, HomeResponse.class);
        return account.getBody();
    }
}
