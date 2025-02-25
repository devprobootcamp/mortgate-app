package com.devprobootcamp.app.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:53 AM<br/>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    @NotBlank(message = "homeId is required")
    private String homeId;

    @NotNull(message = "amount is required")
    private Double amount;

}
