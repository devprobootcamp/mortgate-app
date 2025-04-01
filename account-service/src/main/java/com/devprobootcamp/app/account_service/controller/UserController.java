package com.devprobootcamp.app.account_service.controller;

import com.devprobootcamp.app.account_service.dto.AppToken;
import com.devprobootcamp.app.account_service.dto.UserDTO;
import com.devprobootcamp.app.account_service.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 8:11 AM<br/>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/token")
    public ResponseEntity<AppToken> generateToke(@RequestHeader("Authorization") String basicAuth) {

        String authString = basicAuth.substring(6);
        String decodedAuthStr = new String(Base64.getDecoder().decode(authString));
        String[] splitedAuth = decodedAuthStr.split(":");
        String username = splitedAuth[0];
        String password = splitedAuth[1];

        userService.validateUser(username, password);
        // generate JWT token
        // send the as response
//        String secretKey = "pfWuzG6rNqy232J56xsterB1v90K2oWWfb2pHqXJw43cEBS7VTCmwFD8UU2ytyrrzx6QemD4djhpjTH85PBs1QHLe4mS3YkhjuqcpwumAcMjxfj0G2bgBnVkNymytG7I";
//        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
//        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, SignatureAlgorithm.HS256.getValue());

        String token = Jwts.builder()                     // (1)
                .header()                                   // (2) optional
                .and()
                .subject(username)                             // (3) JSON Claims, or
                //.content(aByteArray, "text/plain")        //     any byte[] content, with media type
                .claim("IsAdmin", "N")
                .claim("isEmailVerify", "Y")
                .expiration(new Date(new Date().getTime() + (60 * 60 * 60)))
                .signWith(UserService.originalKey)                       // (4) if signing, or
                //.encryptWith(key, keyAlg, encryptionAlg)  //     if encrypting
                .compact();

        AppToken appToken = new AppToken();
        appToken.setToken(token);

        return new ResponseEntity<>(appToken, HttpStatus.OK);
    }


}
