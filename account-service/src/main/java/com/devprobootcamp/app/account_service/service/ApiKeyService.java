package com.devprobootcamp.app.account_service.service;

import com.devprobootcamp.app.account_service.entity.ApiKeysEntity;
import com.devprobootcamp.app.account_service.exception.SecurityAccessException;
import com.devprobootcamp.app.account_service.repository.ApiKeysRepository;
import com.devprobootcamp.app.account_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 7:35 AM<br/>
 */
@Service
public class ApiKeyService {


    @Autowired
    private ApiKeysRepository apiKeysRepository;


    public void validateApiKey(String apiKey, String service) {
        Optional<ApiKeysEntity> apiKeysEntityOptional = apiKeysRepository.findByApiKey(apiKey);
        if (apiKeysEntityOptional.isEmpty()) {
            throw new SecurityAccessException("UnAuthorized access");
        }
        ApiKeysEntity apiKeysEntity = apiKeysEntityOptional.get();
        if (!apiKeysEntity.getServices().contains(service)) {
            throw new SecurityAccessException("Service is unauthorized for this key");
        }
    }


}
