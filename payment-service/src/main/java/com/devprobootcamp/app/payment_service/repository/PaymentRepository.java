package com.devprobootcamp.app.payment_service.repository;

import com.devprobootcamp.app.payment_service.entity.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:52 AM<br/>
 */
public interface PaymentRepository extends JpaRepository<PaymentTransactionEntity, String> {
}
