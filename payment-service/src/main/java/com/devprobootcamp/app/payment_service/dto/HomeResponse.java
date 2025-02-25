package com.devprobootcamp.app.payment_service.dto;

import lombok.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:15 AM<br/>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeResponse {


    private String id;
    private String address;  // street, city, state, zipCode, country

    private Double totalPrice;
    private Double interestRate;
    private Double mortgageAmount;
    private Integer loanDuration;

}
