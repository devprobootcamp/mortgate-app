package com.devprobootcamp.app.account_service.service;

import com.devprobootcamp.app.account_service.dto.AccountRequest;
import com.devprobootcamp.app.account_service.dto.AccountResponse;
import com.devprobootcamp.app.account_service.entity.AccountEntity;
import com.devprobootcamp.app.account_service.exception.ResourceNotFoundException;
import com.devprobootcamp.app.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:16 AM<br/>
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest accountRequest) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(accountRequest.getName());
        accountEntity.setEmailAddress(accountRequest.getEmail());

        AccountEntity savedAccount = accountRepository.save(accountEntity);

        return mapAccountResponse(savedAccount);
    }

    public List<AccountResponse> getAllAccount() {

        List<AccountEntity> accounts = accountRepository.findAll();

        List<AccountResponse> response = new ArrayList<>();
        for(AccountEntity acc: accounts){
            response.add(mapAccountResponse(acc));
        }

        return response;
    }

    public AccountResponse getAccount(String id) {

        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
        if(accountEntityOptional.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }

        return mapAccountResponse(accountEntityOptional.get());
    }

     private AccountResponse mapAccountResponse(AccountEntity accountEntity){
         AccountResponse accountResponse = new AccountResponse();
         accountResponse.setId(accountEntity.getId());
         accountResponse.setName(accountEntity.getName());
         accountResponse.setEmail(accountEntity.getEmailAddress());

         return accountResponse;
     }
}
