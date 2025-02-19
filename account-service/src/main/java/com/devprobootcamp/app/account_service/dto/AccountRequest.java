package com.devprobootcamp.app.account_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:12 AM<br/>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
    private String name;

    @Email(message = "email is invalid")
    @NotBlank(message = "email is required")
    private String email;
}
