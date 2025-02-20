package com.devprobootcamp.app.account_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:08 AM<br/>
 */

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;
}
