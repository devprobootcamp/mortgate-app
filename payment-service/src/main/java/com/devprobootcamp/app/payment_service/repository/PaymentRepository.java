package com.devprobootcamp.app.payment_service.repository;

import com.devprobootcamp.app.payment_service.entity.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:52 AM<br/>
 */
public interface PaymentRepository extends JpaRepository<PaymentTransactionEntity, String> {



//    @Query( value = "Select * from transactions where and txn.homeId=:homeId order by payment_date  desc limit 1", nativeQuery = true)
//    @Query( value = "Select * from transactions t1 where payment_date = (select max(t2.payment_date) from transactions t2)) and txn.homeId=:homeId", nativeQuery = true)
    @Query(value="Select txn from PaymentTransactionEntity txn WHERE txn.paymentDate = (select max(txn2.paymentDate) FROM PaymentTransactionEntity txn2) and txn.homeId=:homeId")
    Optional<PaymentTransactionEntity> findLatestPayment(String homeId);
}
