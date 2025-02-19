package com.devprobootcamp.app.account_service.repository;

import com.devprobootcamp.app.account_service.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:11 AM<br/>
 */
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
