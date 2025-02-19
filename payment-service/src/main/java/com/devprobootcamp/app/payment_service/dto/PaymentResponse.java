package com.devprobootcamp.app.payment_service.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:53 AM<br/>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private String homeId;
    private Double amount;
    private Double additionalAmount = 0.0;
    private Double interestAmount;
    private LocalDateTime paymentDate;
    private Double balance;
}
