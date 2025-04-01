package com.devprobootcamp.app.account_service.repository;

import com.devprobootcamp.app.account_service.entity.ApiKeysEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:11 AM<br/>
 */
public interface ApiKeysRepository extends JpaRepository<ApiKeysEntity, String> {

    Optional<ApiKeysEntity> findByApiKey(String apiKey);
}
