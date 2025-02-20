package com.devprobootcamp.app.mortgate_service.service;

import com.devprobootcamp.app.mortgate_service.dto.AccountDTO;
import com.devprobootcamp.app.mortgate_service.dto.HomeRequest;
import com.devprobootcamp.app.mortgate_service.dto.HomeResponse;
import com.devprobootcamp.app.mortgate_service.entity.HomeEntity;
import com.devprobootcamp.app.mortgate_service.exception.ResourceNotFoundException;
import com.devprobootcamp.app.mortgate_service.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:35 AM<br/>
 */
@Service
public class HomeService {


    @Value("${app.service.account-service}")
    private String accountServiceUrl;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createHome(HomeRequest request) {

        HomeEntity homeEntity = new HomeEntity();
        updateHomeEntity(request, homeEntity);

        homeRepository.save(homeEntity);
    }


    public void updateHome(String id, HomeRequest request) {

        Optional<HomeEntity> homeEntityOptional = homeRepository.findById(id);
        if (homeEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("Home not found");
        }
        HomeEntity homeEntity = homeEntityOptional.get();
        updateHomeEntity(request, homeEntity);
        homeRepository.save(homeEntity);

    }

    public List<HomeResponse> getAllHome() {

        List<HomeResponse> response = new ArrayList<>();
        List<HomeEntity> homes = homeRepository.findAll();
        for (HomeEntity home : homes) {
            HomeResponse homeResponse = new HomeResponse();
            homeResponse.setId(home.getId());
            homeResponse.setAddress(home.getStreet() + "," + home.getCity() + "," + home.getState() + "," + home.getZipCode() + "," + home.getCountry());
            homeResponse.setTotalPrice(home.getTotalPrice());
            homeResponse.setMortgageAmount(home.getMortgageAmount());
            homeResponse.setInterestRate(home.getInterestRate());
            homeResponse.setLoanDuration(home.getLoanDuration());

            AccountDTO accountDTO = callAccountService(home.getAccountId());
            homeResponse.setAccount(accountDTO);
            response.add(homeResponse);

        }
        return response;
    }


    private static void updateHomeEntity(HomeRequest request, HomeEntity homeEntity) {
        homeEntity.setStreet(request.getStreet());
        homeEntity.setCity(request.getCity());
        homeEntity.setState(request.getState());
        homeEntity.setZipCode(request.getZipCode());
        homeEntity.setCountry(request.getCountry());
        homeEntity.setTotalPrice(request.getTotalPrice());
        homeEntity.setMortgageAmount(request.getMortgageAmount());
        homeEntity.setInterestRate(request.getInterestRate());
        homeEntity.setLoanDuration(request.getLoanDuration());
        homeEntity.setAccountId(request.getAccountId());
    }


    private AccountDTO callAccountService(String accountId) {
        ResponseEntity<AccountDTO> account = restTemplate.getForEntity(accountServiceUrl + "/" + accountId, AccountDTO.class);
        return account.getBody();
    }
}
