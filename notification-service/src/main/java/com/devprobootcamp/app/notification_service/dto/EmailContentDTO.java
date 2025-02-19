package com.devprobootcamp.app.notification_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:27 AM<br/>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailContentDTO {

    @NotBlank(message = "name is required")
    @Size(max = 50, min = 5, message = "name should be between 5-50 characters")
    private String name;

    @Email(message = "email is invalid")
    @NotBlank(message = "email is required")
    private String emailAddress;

    @NotBlank(message = "content is required")
    private String content;
}
