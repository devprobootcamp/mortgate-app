package com.devprobootcamp.app.mortgate_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    private Integer loanDuration;

    private AccountDTO account;

}
