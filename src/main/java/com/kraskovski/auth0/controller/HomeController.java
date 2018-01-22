package com.kraskovski.auth0.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    protected ResponseEntity<String> home() {
        return ResponseEntity.ok("You've access to the secured API");
    }
}
