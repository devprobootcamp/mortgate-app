package com.devprobootcamp.app.account_service.controller;

import com.devprobootcamp.app.account_service.dto.AccountRequest;
import com.devprobootcamp.app.account_service.dto.AccountResponse;
import com.devprobootcamp.app.account_service.service.AccountService;
import com.devprobootcamp.app.account_service.service.ApiKeyService;
import com.devprobootcamp.app.account_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
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

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest, @RequestHeader("Authorization") String basicAuth) {
        System.out.println(basicAuth);

        String secretKey = "pfWuzG6rNqy232J56xsterB1v90K2oWWfb2pHqXJw43cEBS7VTCmwFD8UU2ytyrrzx6QemD4djhpjTH85PBs1QHLe4mS3YkhjuqcpwumAcMjxfj0G2bgBnVkNymytG7I";

//        String authString = basicAuth.split("Basic ")[0];
        String authString = basicAuth.substring(7);


        userService.validateToken(authString, secretKey);


        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponse>> getAllAccount(@RequestHeader("Authorization") String apiKey) {
        apiKeyService.validateApiKey(apiKey, "ALL_ACCOUNT");
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") String accountId, @RequestHeader("Authorization") String apiKey) {
        apiKeyService.validateApiKey(apiKey, "AN_ACCOUNT");
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }
}
