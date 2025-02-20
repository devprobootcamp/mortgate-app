package com.devprobootcamp.app.mortgate_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:15 AM<br/>
 */
@Entity
@Table(name = "homes")
@Getter
@Setter
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "mortgage_amount", nullable = false)
    private Double mortgageAmount;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "loan_duration", nullable = false)
    private Integer loanDuration;

    @Column(name = "account_id", nullable = false)
    private String accountId;
}
