package com.devprobootcamp.app.payment_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:45 AM<br/>
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
public class PaymentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "home_id", nullable = false)
    private String homeId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "balance", nullable = false)
    private Double balance;

}
