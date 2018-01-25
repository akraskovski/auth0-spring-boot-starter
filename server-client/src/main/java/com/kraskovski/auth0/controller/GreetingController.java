package com.kraskovski.auth0.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API general controller.
 */
@RestController
public class GreetingController {

    /**
     * Not secured endpoint.
     */
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("All good. You DO NOT need to be authenticated to call /login");
    }

    /**
     * Secured endpoint.
     */
    @GetMapping("/secured")
    public ResponseEntity<String> getSecuredEndpoint() {
        return ResponseEntity.ok("Authentication successfully");
    }
}
