package com.devprobootcamp.app.mortgate_service.repository;

import com.devprobootcamp.app.mortgate_service.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:22 AM<br/>
 */
@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, String> {

}
