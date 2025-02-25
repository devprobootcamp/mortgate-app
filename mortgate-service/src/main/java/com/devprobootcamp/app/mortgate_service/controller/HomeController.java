package com.devprobootcamp.app.mortgate_service.controller;

import com.devprobootcamp.app.mortgate_service.dto.HomeRequest;
import com.devprobootcamp.app.mortgate_service.dto.HomeResponse;
import com.devprobootcamp.app.mortgate_service.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 7:38 AM<br/>
 */
@RestController
@RequestMapping("/homes")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @PostMapping()
    public ResponseEntity<Void> createHome(@RequestBody HomeRequest homeRequest) {
        homeService.createHome(homeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updateHome(@PathVariable("id") String id, @RequestBody HomeRequest homeRequest) {
        homeService.updateHome(id, homeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<HomeResponse>> getAllHome() {
        List<HomeResponse> homes = homeService.getAllHome();
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<HomeResponse> getHome(@PathVariable("homeId") String homeId) {
        HomeResponse home = homeService.getHome(homeId);
        return new ResponseEntity<>(home, HttpStatus.OK);
    }



}
