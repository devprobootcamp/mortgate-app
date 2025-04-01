package com.devprobootcamp.app.account_service.repository;

import com.devprobootcamp.app.account_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 8:11 AM<br/>
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
    Optional<UserEntity> findByUsername(String username);
}
