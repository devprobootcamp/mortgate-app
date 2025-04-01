package com.devprobootcamp.app.account_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 7:32 AM<br/>
 */
@Entity
@Table(name = "api_keys")
@Getter
@Setter
public class ApiKeysEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "services", nullable = false)
    private String services;


}
