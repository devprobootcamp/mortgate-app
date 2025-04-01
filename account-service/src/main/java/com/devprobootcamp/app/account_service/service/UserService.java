package com.devprobootcamp.app.account_service.service;

import com.devprobootcamp.app.account_service.dto.UserDTO;
import com.devprobootcamp.app.account_service.entity.ApiKeysEntity;
import com.devprobootcamp.app.account_service.entity.UserEntity;
import com.devprobootcamp.app.account_service.exception.ResourceAlreadyExistException;
import com.devprobootcamp.app.account_service.exception.SecurityAccessException;
import com.devprobootcamp.app.account_service.repository.ApiKeysRepository;
import com.devprobootcamp.app.account_service.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 7:35 AM<br/>
 */
@Service
public class UserService {

    public static  SecretKey originalKey = Jwts.SIG.HS256.key().build();



    @Autowired
    private UserRepository userRepository;


    public void createUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDTO.getUsername());
        if (userEntityOptional.isPresent()) {
            throw new ResourceAlreadyExistException("User already exist");
        }

        String hashedPwd = hashPassword(userDTO.getPassword());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(hashedPwd);

        userRepository.save(userEntity);

    }


    public void validateUser(String username, String password) {

        String hashedPassword = hashPassword(password);

        Optional<UserEntity> userEntityOptional = userRepository.findByUsernameAndPassword(username, hashedPassword);
        if (userEntityOptional.isEmpty()) {
            throw new SecurityAccessException("Wrong username or password.");
        }

    }


    public Jws<Claims> validateToken(String token, String secretKey) {

//        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
//        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        try {
            return Jwts.parser().verifyWith(originalKey).build().parseSignedClaims(token);
        } catch (Exception ex) {
            throw new SecurityAccessException("Invalid token");
        }

    }


    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltedPassword = password.getBytes(StandardCharsets.UTF_8);
            byte[] hashBytes = digest.digest(saltedPassword);
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not found", e);
        }
    }
}
