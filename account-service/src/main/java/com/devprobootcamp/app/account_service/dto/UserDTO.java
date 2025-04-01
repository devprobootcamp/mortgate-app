package com.devprobootcamp.app.account_service.dto;

import lombok.*;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 8:15 AM<br/>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String name;
    private String username;
    private String password;
}
