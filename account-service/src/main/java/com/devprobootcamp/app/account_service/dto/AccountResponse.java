package com.devprobootcamp.app.account_service.dto;

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
public class AccountResponse {

    private String id;
    private String name;
    private String email;
}
