package com.devprobootcamp.app.account_service.controller;

import com.devprobootcamp.app.account_service.dto.AccountRequest;
import com.devprobootcamp.app.account_service.dto.AccountResponse;
import com.devprobootcamp.app.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:18 AM<br/>
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponse>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") String accountId) {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.CREATED);
    }
}
