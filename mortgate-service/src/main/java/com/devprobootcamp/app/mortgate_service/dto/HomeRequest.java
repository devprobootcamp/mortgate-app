package com.devprobootcamp.app.mortgate_service.dto;

import jakarta.persistence.*;
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
public class HomeRequest {

    @NotBlank(message = "street is required")
    @Size(max = 100, min = 5, message = "street should be between 5-100 characters")
    private String street;

    @NotBlank(message = "city is required")
    @Size(max = 20, min = 2, message = "city should be between 2-20 characters")
    private String city;

    @NotBlank(message = "state is required")
    @Size(max = 20, min = 2, message = "state should be between 2-20 characters")
    private String state;

    @NotBlank(message = "zipCode is required")
    @Size(max = 20, min = 2, message = "zipCode should be between 2-20 characters")
    private String zipCode;

    @NotBlank(message = "country is required")
    @Size(max = 100, min = 5, message = "country should be between 5-100 characters")
    private String country;

    @NotNull(message = "totalPrice is required")
    private Double totalPrice;

    @NotNull(message = "mortgageAmount is required")
    private Double mortgageAmount;

    @NotNull(message = "interestRate is required")
    private Double interestRate;

    @NotNull(message = "loanDuration is required")
    private Integer loanDuration;

    @NotBlank(message = "accountId is required")
    private String accountId;
}
